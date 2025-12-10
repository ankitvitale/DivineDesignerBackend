package com.DivineDesignerDen.Service;


import com.DivineDesignerDen.DTO.OrderResponseDTO;
import com.DivineDesignerDen.DTO.PaymentHistoryDTO;
import com.DivineDesignerDen.DTO.PaymentRequestDTO;
import com.DivineDesignerDen.Entity.PaymentHistory;
import com.DivineDesignerDen.Entity.TailorOrder;
import com.DivineDesignerDen.Repository.PaymentHistoryRepository;
import com.DivineDesignerDen.Repository.TailorOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentHistoryService {

    @Autowired
    private TailorOrderRepository orderRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    // UPDATE PAYMENT
    public OrderResponseDTO updatePayment(Long paymentId, PaymentRequestDTO dto) {

        PaymentHistory payment = paymentHistoryRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment record not found"));

        TailorOrder order = payment.getOrder();

        double oldAmount = payment.getAmount();

        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getMethod());
        payment.setPaymentDate(LocalDate.now());

        paymentHistoryRepository.save(payment);

        double newAdvance = order.getAdvance() - oldAmount + dto.getAmount();
        double newBalance = order.getTotal() - newAdvance;

        order.setAdvance(newAdvance);
        order.setBalance(newBalance);

        orderRepository.save(order);

        return mapToOrderDTO(order);
    }


    // DELETE PAYMENT
    public OrderResponseDTO deletePayment(Long paymentId) {

        PaymentHistory payment = paymentHistoryRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment record not found"));

        TailorOrder order = payment.getOrder();

        double updatedAdvance = order.getAdvance() - payment.getAmount();
        double updatedBalance = order.getTotal() - updatedAdvance;

        order.setAdvance(updatedAdvance);
        order.setBalance(updatedBalance);

        orderRepository.save(order);

        paymentHistoryRepository.delete(payment);

        return mapToOrderDTO(order);
    }


    // GET PAYMENT BY ID
    public PaymentHistoryDTO getPaymentById(Long paymentId) {

        PaymentHistory payment = paymentHistoryRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment record not found"));

        PaymentHistoryDTO dto = new PaymentHistoryDTO();
        dto.setId(payment.getId());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());
        dto.setMethod(payment.getPaymentMethod());

        return dto;
    }


    // MAPPING METHOD
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
