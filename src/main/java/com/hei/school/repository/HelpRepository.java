package com.hei.school.repository;

import com.hei.school.model.Help;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpRepository extends JpaRepository<Help, Long> {
  @Query("SELECT h FROM Help h ORDER BY h.payment.date DESC")
  List<Help> findAllByOrderByPaymentDateDesc();
}
