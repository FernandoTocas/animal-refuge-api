package com.fpsumma.psp.animalrefuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpsumma.psp.animalrefuge.model.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository <Shelter, Long> {
    
}
