package com.DivineDesignerDen.Controller;

import com.DivineDesignerDen.DTO.KapdaRequest;
import com.DivineDesignerDen.DTO.StockEntryRequest;
import com.DivineDesignerDen.Entity.Kapda;
import com.DivineDesignerDen.Entity.StockHistory;
import com.DivineDesignerDen.Service.KapdaService;
import com.DivineDesignerDen.Service.KapdaStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kapda")
public class KapdaController {

    @Autowired
    private KapdaService kapdaService;


    @Autowired
    private KapdaStockService kapdaStockService;

    @PostMapping
    public ResponseEntity<Kapda> addKapda(@RequestBody KapdaRequest request) {
        return ResponseEntity.ok(kapdaService.addKapda(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kapda> updateKapda(@PathVariable Long id, @RequestBody KapdaRequest request) {
        return ResponseEntity.ok(kapdaService.updateKapda(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kapda> getKapda(@PathVariable Long id) {
        return ResponseEntity.ok(kapdaService.getKapda(id));
    }

    @GetMapping
    public ResponseEntity<List<Kapda>> getAllKapda() {
        return ResponseEntity.ok(kapdaService.getAllKapda());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKapda(@PathVariable Long id) {
        kapdaService.deleteKapda(id);
        return ResponseEntity.ok("Kapda deleted");
    }


    @PostMapping("/{id}/stock")
    public ResponseEntity<Kapda> updateStock(
            @PathVariable Long id,
            @RequestBody StockEntryRequest request) {

        return ResponseEntity.ok(kapdaStockService.updateStock(id, request));
    }

    @GetMapping("/{id}/stock-history")
    public ResponseEntity<List<StockHistory>> getStockHistory(@PathVariable Long id) {
        return ResponseEntity.ok(kapdaStockService.getStockHistory(id));
    }
}
