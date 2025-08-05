package com.hei.school.service;

import com.hei.school.dto.VolaPaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VolaService {
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${vola.api.url}")
  private String volaApiUrl;

  @Value("${vola.api.key}")
  private String apiKey;

  public VolaPaymentResponse createPayment(String payerEmail, String pspPaymentId) {
    String url =
        String.format(
            "%s/payment?apiKey=%s&payerEmail=%s&pspType=ORANGE_MONEY&pspPaymentId=%s",
            volaApiUrl, apiKey, payerEmail, pspPaymentId);

    return restTemplate.postForObject(url, null, VolaPaymentResponse.class);
  }

  public VolaPaymentResponse getPaymentStatus(String payerEmail, String pspPaymentId) {
    String url =
        String.format(
            "%s/payment?apiKey=%s&payerEmail=%s&pspType=ORANGE_MONEY&pspPaymentId=%s",
            volaApiUrl, apiKey, payerEmail, pspPaymentId);

    return restTemplate.getForObject(url, VolaPaymentResponse.class);
  }
}
