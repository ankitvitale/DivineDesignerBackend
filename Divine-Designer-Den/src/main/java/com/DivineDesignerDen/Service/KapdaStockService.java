package com.DivineDesignerDen.Service;

import com.DivineDesignerDen.DTO.StockEntryRequest;
import com.DivineDesignerDen.Entity.Kapda;
import com.DivineDesignerDen.Entity.StockHistory;
import com.DivineDesignerDen.Repository.KapdaRepository;
import com.DivineDesignerDen.Repository.StockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KapdaStockService {

    @Autowired
    private KapdaRepository kapdaRepository;

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    public Kapda updateStock(Long kapdaId, StockEntryRequest request) {

        Kapda k = kapdaRepository.findById(kapdaId)
                .orElseThrow(() -> new RuntimeException("Kapda not found " + kapdaId));

        StockHistory history = new StockHistory();
        history.setKapda(k);
        history.setDate(LocalDate.now());
        history.setNote(request.getNote());
        history.setType(request.getType());
        history.setQuantity(request.getQuantity());

        stockHistoryRepository.save(history);

        if (request.getType().equalsIgnoreCase("IN")) {
            k.setTotalIn(k.getTotalIn() + request.getQuantity());
        } else if (request.getType().equalsIgnoreCase("OUT")) {
            k.setTotalOut(k.getTotalOut() + request.getQuantity());
        }

        k.setCurrentStock(k.getTotalIn() - k.getTotalOut());

        return kapdaRepository.save(k);
    }

    public List<StockHistory> getStockHistory(Long kapdaId) {
        return stockHistoryRepository.findByKapdaIdOrderByDateDesc(kapdaId);
    }
}
