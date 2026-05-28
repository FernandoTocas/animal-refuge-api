package com.fpsumma.psp.animalrefuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpsumma.psp.animalrefuge.model.CarePlan;

@Repository
public interface CarePlanRepository extends JpaRepository <CarePlan, Long> {
    
}
