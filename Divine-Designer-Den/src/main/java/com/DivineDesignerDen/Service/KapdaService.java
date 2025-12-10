package com.DivineDesignerDen.Service;

import com.DivineDesignerDen.DTO.KapdaRequest;
import com.DivineDesignerDen.Entity.Kapda;
import com.DivineDesignerDen.Repository.KapdaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KapdaService {

    @Autowired
    private KapdaRepository kapdaRepository;

    public Kapda addKapda(KapdaRequest request) {

        Kapda k = new Kapda();

        k.setBarcode(request.getBarcode());
        k.setName(request.getName());
        k.setType(request.getType());
        k.setColor(request.getColor());

        // Initial quantity stock
        k.setQuantity(request.getQuantity());   // if Kapda has this field
        k.setUnit(request.getUnit());

        k.setPricePerUnit(request.getPricePerUnit());
        k.setSupplier(request.getSupplier());
        k.setDateAdded(request.getDateAdded());

        // Stock calculations
        k.setTotalIn(request.getQuantity());   // initial stock IN
        k.setTotalOut(0);
        k.setCurrentStock(request.getQuantity());

        return kapdaRepository.save(k);
    }


    public Kapda updateKapda(Long kapdaId, KapdaRequest request) {

        Kapda k = kapdaRepository.findById(kapdaId)
                .orElseThrow(() -> new RuntimeException("Kapda not found"));

        k.setBarcode(request.getBarcode());
        k.setName(request.getName());
        k.setType(request.getType());
        k.setColor(request.getColor());

        // Update quantity and stock
        double oldCurrentStock = k.getCurrentStock();
        double oldTotalIn = k.getTotalIn();

        double newQuantity = request.getQuantity();

        // Adjust stock values
        k.setQuantity(newQuantity);   // if Kapda has this field
        k.setUnit(request.getUnit());
        k.setPricePerUnit(request.getPricePerUnit());
        k.setSupplier(request.getSupplier());
        k.setDateAdded(request.getDateAdded());

        // Adjust stock totals
        double diff = newQuantity - oldCurrentStock;
        k.setTotalIn(oldTotalIn + (diff > 0 ? diff : 0)); // only increase totalIn if quantity increased
        k.setCurrentStock(newQuantity);

        return kapdaRepository.save(k);
    }


    public Kapda getKapda(Long id) {
        return kapdaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kapda not found " + id));
    }

    public List<Kapda> getAllKapda() {
        return kapdaRepository.findAll();
    }

    public void deleteKapda(Long id) {
        kapdaRepository.deleteById(id);
    }
}
