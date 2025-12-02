package com.DivineDesignerDen.Repository;

import com.DivineDesignerDen.Entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory,Long> {
}
