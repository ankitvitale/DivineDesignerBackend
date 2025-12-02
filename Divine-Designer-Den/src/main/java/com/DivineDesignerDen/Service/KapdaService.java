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
        BeanUtils.copyProperties(request, k);
        return kapdaRepository.save(k);
    }

    public Kapda updateKapda(Long id, KapdaRequest request) {
        Kapda k = kapdaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kapda not found " + id));

        BeanUtils.copyProperties(request, k);

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
