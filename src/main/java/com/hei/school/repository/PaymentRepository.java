package com.hei.school.repository;

import com.hei.school.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
  List<Payment> findByStatus(String status);
}
