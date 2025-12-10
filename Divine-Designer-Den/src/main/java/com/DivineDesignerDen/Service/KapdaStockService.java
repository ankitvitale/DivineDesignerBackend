package com.DivineDesignerDen.Service;

import com.DivineDesignerDen.DTO.KapdaHistoryResponse;
import com.DivineDesignerDen.DTO.KapdaTransactionDTO;
import com.DivineDesignerDen.DTO.StockEntryRequest;
import com.DivineDesignerDen.Entity.Kapda;
import com.DivineDesignerDen.Entity.StockHistory;
import com.DivineDesignerDen.Repository.KapdaRepository;
import com.DivineDesignerDen.Repository.StockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KapdaStockService {

    @Autowired
    private KapdaRepository kapdaRepository;

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

//    public Kapda updateStock(Long kapdaId, StockEntryRequest request) {
//
//        Kapda k = kapdaRepository.findById(kapdaId)
//                .orElseThrow(() -> new RuntimeException("Kapda not found " + kapdaId));
//
//        StockHistory history = new StockHistory();
//        history.setKapda(k);
//        history.setDate(LocalDate.now());
//        history.setNote(request.getNote());
//        history.setType(request.getType());
//        history.setQuantity(request.getQuantity());
//
//        stockHistoryRepository.save(history);
//
//        if (request.getType().equalsIgnoreCase("IN")) {
//            k.setTotalIn(k.getTotalIn() + request.getQuantity());
//        } else if (request.getType().equalsIgnoreCase("OUT")) {
//            k.setTotalOut(k.getTotalOut() + request.getQuantity());
//        }
//
//        k.setCurrentStock(k.getTotalIn() - k.getTotalOut());
//
//        return kapdaRepository.save(k);
//    }

    public Kapda updateStock(Long kapdaId, StockEntryRequest request) {

        Kapda k = kapdaRepository.findById(kapdaId)
                .orElseThrow(() -> new RuntimeException("Kapda not found " + kapdaId));

        StockHistory history = new StockHistory();
        history.setKapda(k);
        history.setDate(LocalDate.now());
        history.setNote(request.getNote());
        history.setType(request.getType());

        double qty = request.getQuantity();

        if (request.getType().equalsIgnoreCase("IN")) {
            history.setQuantity(qty);            // store positive
            k.setTotalIn(k.getTotalIn() + qty);  // update total IN
        }
        else if (request.getType().equalsIgnoreCase("OUT")) {
            history.setQuantity(qty);             // store negative
            k.setTotalOut(k.getTotalOut() + qty);  // update total OUT
        }
        else {
            throw new RuntimeException("Type must be IN or OUT");
        }

        stockHistoryRepository.save(history);

        // Calculate final stock
        k.setCurrentStock(k.getTotalIn() - k.getTotalOut());

        return kapdaRepository.save(k);
    }


    public KapdaHistoryResponse getKapdaHistory(Long kapdaId) {

        // 1️⃣ Fetch Kapda entity
        Kapda kapda = kapdaRepository.findById(kapdaId)
                .orElseThrow(() -> new RuntimeException("Kapda not found"));

        // 2️⃣ Fetch stock history for this Kapda
        List<StockHistory> historyList = stockHistoryRepository.findByKapdaIdOrderByDateDesc(kapdaId);

        // 3️⃣ Prepare response
        KapdaHistoryResponse response = new KapdaHistoryResponse();
        response.setKapdaId(kapda.getId());
        response.setBarcode(kapda.getBarcode());
        response.setName(kapda.getName());
        response.setType(kapda.getType());
        response.setColor(kapda.getColor());
        response.setUnit(kapda.getUnit());
        response.setPricePerUnit(kapda.getPricePerUnit());
        response.setSupplier(kapda.getSupplier());
        response.setDateAdded(kapda.getDateAdded());
        response.setTotalIn(kapda.getTotalIn());
        response.setTotalOut(kapda.getTotalOut());
        response.setCurrentStock(kapda.getCurrentStock());

        // 4️⃣ Convert history list to DTO
        List<KapdaTransactionDTO> historyDtoList = historyList.stream()
                .map(h -> {
                    KapdaTransactionDTO dto = new KapdaTransactionDTO();
                    dto.setId(h.getId());
                    dto.setDate(h.getDate());
                    dto.setType(h.getType()); // Already String, no need to call .toString()
                    dto.setQuantity(h.getQuantity());
                    dto.setNote(h.getNote());
                    return dto;
                })
                .collect(Collectors.toList());

        response.setHistory(historyDtoList);

        return response;
    }


    public Kapda editStock(Long stockId, StockEntryRequest request) {

        StockHistory history = stockHistoryRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock history not found " + stockId));

        Kapda k = history.getKapda();

        // Subtract old quantity from totals
        if (history.getType().equalsIgnoreCase("IN")) {
            k.setTotalIn(k.getTotalIn() - history.getQuantity());
        } else if (history.getType().equalsIgnoreCase("OUT")) {
            k.setTotalOut(k.getTotalOut() - history.getQuantity());
        }

        // Update StockHistory
        history.setDate(LocalDate.now());
        history.setNote(request.getNote());
        history.setType(request.getType());
        double qty = request.getQuantity();
        history.setQuantity(qty);

        // Add new quantity to totals
        if (request.getType().equalsIgnoreCase("IN")) {
            k.setTotalIn(k.getTotalIn() + qty);
        } else if (request.getType().equalsIgnoreCase("OUT")) {
            k.setTotalOut(k.getTotalOut() + qty);
        } else {
            throw new RuntimeException("Type must be IN or OUT");
        }

        stockHistoryRepository.save(history);

        // Recalculate current stock
        k.setCurrentStock(k.getTotalIn() - k.getTotalOut());

        return kapdaRepository.save(k);
    }
    public Kapda deleteStock(Long stockId) {

        StockHistory history = stockHistoryRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock history not found " + stockId));

        Kapda k = history.getKapda();

        // Subtract from totals
        if (history.getType().equalsIgnoreCase("IN")) {
            k.setTotalIn(k.getTotalIn() - history.getQuantity());
        } else if (history.getType().equalsIgnoreCase("OUT")) {
            k.setTotalOut(k.getTotalOut() - history.getQuantity());
        }

        // Recalculate current stock
        k.setCurrentStock(k.getTotalIn() - k.getTotalOut());

        // Delete stock history
        stockHistoryRepository.delete(history);

        return kapdaRepository.save(k);
    }

    public StockHistory getStockById(Long stockId) {
        return stockHistoryRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock history not found with id: " + stockId));
    }

}
