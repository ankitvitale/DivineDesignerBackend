package com.DivineDesignerDen.Controller;

import com.DivineDesignerDen.DTO.TailorOrderRequest;
import com.DivineDesignerDen.Entity.TailorOrder;
import com.DivineDesignerDen.Service.TailorOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Customer-orders")
@PreAuthorize("hasRole('Admin')")
public class TailorOrderController {

    @Autowired
    private TailorOrderService orderService;

    @PostMapping
    public ResponseEntity<TailorOrder> createOrder(@RequestBody TailorOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<TailorOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TailorOrder> updateOrder(@PathVariable Long id, @RequestBody TailorOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TailorOrder> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

}


