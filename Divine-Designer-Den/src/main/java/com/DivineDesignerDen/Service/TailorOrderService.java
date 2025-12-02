package com.DivineDesignerDen.Service;

import com.DivineDesignerDen.DTO.GarmentDTO;
import com.DivineDesignerDen.DTO.TailorOrderRequest;
import com.DivineDesignerDen.Entity.Garment;
import com.DivineDesignerDen.Entity.Measurement;
import com.DivineDesignerDen.Entity.TailorOrder;

import com.DivineDesignerDen.Repository.TailorOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TailorOrderService {

    @Autowired
    private TailorOrderRepository orderRepository;

    public TailorOrder createOrder(TailorOrderRequest request) {

        TailorOrder order = new TailorOrder();
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

        return orderRepository.save(order);
    }

    public List<TailorOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public TailorOrder updateOrder(Long id, TailorOrderRequest request) {
        TailorOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        order.getGarments().clear();

        setOrderFields(order, request);

        return orderRepository.save(order);
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
