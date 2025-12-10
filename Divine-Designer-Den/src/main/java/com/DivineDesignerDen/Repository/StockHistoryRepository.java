package com.DivineDesignerDen.Repository;

import com.DivineDesignerDen.Entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    List<StockHistory> findByKapdaIdOrderByDateDesc(Long kapdaId);

    List<StockHistory> findByKapdaId(Long kapdaId);
}
