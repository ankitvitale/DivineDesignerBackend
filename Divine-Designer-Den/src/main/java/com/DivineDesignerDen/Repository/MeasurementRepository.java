package com.DivineDesignerDen.Repository;

import com.DivineDesignerDen.Entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {}