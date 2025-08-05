package com.hei.school.endpoint.rest.controller;

import com.hei.school.dto.VolaPaymentResponse;
import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.model.Payment;
import com.hei.school.repository.DonationRepository;
import com.hei.school.repository.DonorRepository;
import com.hei.school.repository.HelpRepository;
import com.hei.school.repository.PaymentRepository;
import com.hei.school.service.VolaService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TsinjoController {
  private final DonationRepository donationRepo;
  private final HelpRepository helpRepo;
  private final VolaService volaService;
  private final PaymentRepository paymentRepo;
  private final DonorRepository donorRepo;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("donations", donationRepo.findAllByOrderByPaymentDateDesc());
    model.addAttribute("helps", helpRepo.findAllByOrderByPaymentDateDesc());
    return "index";
  }

  @PostMapping("/donate")
  public String createDonation(
      @RequestParam String fullName,
      @RequestParam String email,
      @RequestParam String pspPaymentId) {

    // Create payment with Vola
    VolaPaymentResponse response = volaService.createPayment(email, pspPaymentId);

    // Save payment
    Payment payment = new Payment();
    payment.setId(response.getId());
    payment.setPayerEmail(email);
    payment.setPspPaymentId(pspPaymentId);
    payment.setStatus(response.getVerificationStatus());
    payment.setDate(LocalDateTime.now());
    payment.setAmount(BigDecimal.valueOf(response.getPspPayment().getAmount()));
    payment.setMethod("ORANGE_MONEY");
    paymentRepo.save(payment);

    // Save donor
    Donor donor = new Donor();
    donor.setEmail(email);
    donor.setFullName(fullName);
    donorRepo.save(donor);

    // Save donation
    Donation donation = new Donation();
    donation.setDonor(donor);
    donation.setPayment(payment);
    donationRepo.save(donation);

    return "redirect:/";
  }
}
