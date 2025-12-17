package com.DivineDesignerDen.Service;

import com.DivineDesignerDen.DTO.GarmentDTO;
import com.DivineDesignerDen.DTO.TailorOrderRequest;
import com.DivineDesignerDen.Entity.Garment;
import com.DivineDesignerDen.Entity.Measurement;
import com.DivineDesignerDen.Entity.PaymentHistory;
import com.DivineDesignerDen.Entity.TailorOrder;

import com.DivineDesignerDen.Repository.PaymentHistoryRepository;
import com.DivineDesignerDen.Repository.TailorOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TailorOrderService {

    @Autowired
    private TailorOrderRepository orderRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    public TailorOrder createOrder(TailorOrderRequest request) {

        TailorOrder order = new TailorOrder();
        order.setOrderNo(request.getOrderNo());
        order.setCustomerName(request.getCustomerName());
        order.setMobile(request.getMobile());
        order.setOrderDate(request.getOrderDate());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setUp1(request.isUp1());
        order.setUp2(request.isUp2());
        order.setUp3(request.isUp3());
        order.setUp4(request.isUp4());
        order.setDown1(request.isDown1());
        order.setDown2(request.isDown2());
        order.setDown3(request.isDown3());
        order.setDown4(request.isDown4());
        order.setTotal(request.getTotal());
        order.setAdvance(request.getAdvance());
        order.setBalance(request.getBalance());

        List<Garment> garmentList = new ArrayList<>();

        for (GarmentDTO dto : request.getGarments()) {

            Garment garment = new Garment();
            garment.setType(dto.getType());
            garment.setQuantity(dto.getQuantity());
            garment.setOrder(order); // important

            Measurement m = new Measurement();

            m.setLambai(dto.getMeasurement().getLambai());
            m.setPet(dto.getMeasurement().getPet());
            m.setChati(dto.getMeasurement().getChati());
            m.setSeat(dto.getMeasurement().getSeat());
            m.setShoulder(dto.getMeasurement().getShoulder());
            m.setAsteen(dto.getMeasurement().getAsteen());
            m.setGala(dto.getMeasurement().getGala());
            m.setKaf(dto.getMeasurement().getKaf());
            m.setMohri(dto.getMeasurement().getMohri());

            // pant/pajama
            m.setKamar(dto.getMeasurement().getKamar());
            m.setJang(dto.getMeasurement().getJang());
            m.setLatka(dto.getMeasurement().getLatka());
            m.setBottom(dto.getMeasurement().getBottom());
            m.setGhutna(dto.getMeasurement().getGhutna());
            m.setNote(dto.getMeasurement().getNote());

            m.setGarment(garment);    // IMPORTANT
            garment.setMeasurement(m); // IMPORTANT

            garmentList.add(garment);
        }

        order.setGarments(garmentList);

       // return orderRepository.save(order);


        // SAVE order first
        TailorOrder savedOrder = orderRepository.save(order);


        // 🔥 ADD PAYMENT HISTORY IF ADVANCE > 0
        if (request.getAdvance() > 0) {
            PaymentHistory history = new PaymentHistory();
            history.setAmount(request.getAdvance());
            history.setPaymentMethod("CASH"); // or UPI or request.getMethod()
            history.setPaymentDate(LocalDate.now());
            history.setOrder(savedOrder);

            paymentHistoryRepository.save(history);
        }

        return savedOrder;

    }

    public List<TailorOrder> getAllOrders() {
        return orderRepository.findAll();
    }


    @Transactional
    public TailorOrder updateOrder(Long orderId, TailorOrderRequest request) {

        TailorOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderNo(request.getOrderNo());
        order.setCustomerName(request.getCustomerName());
        order.setMobile(request.getMobile());
        order.setOrderDate(request.getOrderDate());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setTotal(request.getTotal());
        order.setAdvance(request.getAdvance());
        order.setBalance(request.getBalance());
        order.setUp1(request.isUp1());
        order.setUp2(request.isUp2());
        order.setUp3(request.isUp3());
        order.setUp4(request.isUp4());
        order.setDown1(request.isDown1());
        order.setDown2(request.isDown2());
        order.setDown3(request.isDown3());
        order.setDown4(request.isDown4());

        // 🔥 Remove old garments
        order.getGarments().clear();

        for (GarmentDTO dto : request.getGarments()) {

            Garment garment = new Garment();
            garment.setType(dto.getType());
            garment.setQuantity(dto.getQuantity());
            garment.setOrder(order);

            Measurement m = new Measurement();
            m.setLambai(dto.getMeasurement().getLambai());
            m.setPet(dto.getMeasurement().getPet());
            m.setChati(dto.getMeasurement().getChati());
            m.setSeat(dto.getMeasurement().getSeat());
            m.setShoulder(dto.getMeasurement().getShoulder());
            m.setAsteen(dto.getMeasurement().getAsteen());
            m.setGala(dto.getMeasurement().getGala());
            m.setKaf(dto.getMeasurement().getKaf());
            m.setMohri(dto.getMeasurement().getMohri());
            m.setKamar(dto.getMeasurement().getKamar());
            m.setJang(dto.getMeasurement().getJang());
            m.setLatka(dto.getMeasurement().getLatka());
            m.setBottom(dto.getMeasurement().getBottom());
            m.setGhutna(dto.getMeasurement().getGhutna());
            m.setNote(dto.getMeasurement().getNote());

            m.setGarment(garment);
            garment.setMeasurement(m);

            order.getGarments().add(garment);
        }

        // ✅ Save updated order
        TailorOrder updatedOrder = orderRepository.save(order);

        // 🔥 ADD PAYMENT HISTORY IF ADVANCE > 0
        if (request.getAdvance() > 0) {
            PaymentHistory history = new PaymentHistory();
            history.setAmount(request.getAdvance());
            history.setPaymentMethod("CASH"); // or from request
            history.setPaymentDate(LocalDate.now());
            history.setOrder(updatedOrder);

            paymentHistoryRepository.save(history);
        }

        return updatedOrder;
    }


    public TailorOrder getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private void setOrderFields(TailorOrder order, TailorOrderRequest request) {
        order.setOrderNo(request.getOrderNo());
        order.setCustomerName(request.getCustomerName());
        order.setMobile(request.getMobile());
        order.setOrderDate(request.getOrderDate());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setTotal(request.getTotal());
        order.setAdvance(request.getAdvance());
        order.setBalance(request.getBalance());

        List<Garment> garmentList = new ArrayList<>();

        for (GarmentDTO dto : request.getGarments()) {
            Garment garment = new Garment();
            garment.setType(dto.getType());
            garment.setQuantity(dto.getQuantity());
            garment.setOrder(order);

            Measurement m = new Measurement();
            BeanUtils.copyProperties(dto.getMeasurement(), m);
            garment.setMeasurement(m);

            garmentList.add(garment);
        }

        order.setGarments(garmentList);
    }
}
