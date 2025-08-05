package com.hei.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolaPaymentResponse {
  private String id;
  private String verificationStatus;
  private PspPayment pspPayment;

  @Getter
  @Setter
  public static class PspPayment {
    private String id;
    private Integer amount;
  }
}
