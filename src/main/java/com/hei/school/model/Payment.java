package com.hei.school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
  @Id private String id; // ID de Vola

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal amount;

  @Column(nullable = false, length = 50)
  private String method; // ORANGE_MONEY, etc.

  @Column(nullable = false, length = 20)
  private String status; // VERIFYING, SUCCEEDED, FAILED

  @Column(name = "payer_email", nullable = false)
  private String payerEmail;

  @Column(name = "psp_payment_id", nullable = false)
  private String pspPaymentId;
}
