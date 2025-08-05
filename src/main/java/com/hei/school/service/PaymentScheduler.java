package com.hei.school.service;

import com.hei.school.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentScheduler {
  private final VolaService volaService;
  private final PaymentRepository paymentRepository;

  @Scheduled(fixedRate = 300000) // 5 minutes
  public void verifyPendingPayments() {
    paymentRepository
        .findByStatus("VERIFYING")
        .forEach(
            payment -> {
              try {
                var response =
                    volaService.getPaymentStatus(
                        payment.getPayerEmail(), payment.getPspPaymentId());

                payment.setStatus(response.getVerificationStatus());
                paymentRepository.save(payment);
              } catch (Exception e) {
                // Log error
              }
            });
  }
}
