package com.DivineDesignerDen.Controller;

import com.DivineDesignerDen.DTO.*;
import com.DivineDesignerDen.Entity.PaymentHistory;
import com.DivineDesignerDen.Entity.TailorOrder;
import com.DivineDesignerDen.Repository.PaymentHistoryRepository;
import com.DivineDesignerDen.Repository.TailorOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Payment")
public class PaymentHistoryController {
    @Autowired
    private TailorOrderRepository orderRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @PostMapping("/orders/{orderId}/pay")
    public ResponseEntity<OrderResponseDTO> addPayment(
            @PathVariable Long orderId,
            @RequestBody PaymentRequestDTO dto) {

        TailorOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        PaymentHistory history = new PaymentHistory();
        history.setAmount(dto.getAmount());
        history.setPaymentMethod(dto.getMethod());
        history.setPaymentDate(LocalDate.now());
        history.setOrder(order);

        paymentHistoryRepository.save(history);

        double newAdvance = order.getAdvance() + dto.getAmount();
        double newBalance = order.getTotal() - newAdvance;

        order.setAdvance(newAdvance);
        order.setBalance(newBalance);

        orderRepository.save(order);

        return ResponseEntity.ok(mapToOrderDTO(order));
    }
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderWithPayments(@PathVariable Long orderId) {

        TailorOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return ResponseEntity.ok(mapToOrderDTO(order));
    }

    private OrderResponseDTO mapToOrderDTO(TailorOrder order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setOrderNo(order.getOrderNo());
        dto.setCustomerName(order.getCustomerName());
        dto.setMobile(order.getMobile());
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setTotal(order.getTotal());
        dto.setAdvance(order.getAdvance());
        dto.setBalance(order.getBalance());

        // Convert all payment history entries to DTOs
        List<PaymentHistoryDTO> paymentList = order.getPaymentHistoryList()
                .stream()
                .map(history -> {
                    PaymentHistoryDTO ph = new PaymentHistoryDTO();
                    ph.setId(history.getId());
                    ph.setPaymentDate(history.getPaymentDate());
                    ph.setAmount(history.getAmount());
                    ph.setMethod(history.getPaymentMethod());
                    return ph;
                })
                .collect(Collectors.toList());

        dto.setPaymentHistory(paymentList);

        return dto;
    }


}
