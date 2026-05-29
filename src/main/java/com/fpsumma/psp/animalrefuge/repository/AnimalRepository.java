package com.fpsumma.psp.animalrefuge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpsumma.psp.animalrefuge.model.Animal;
import com.fpsumma.psp.animalrefuge.model.AnimalStatus;

@Repository
public interface AnimalRepository extends JpaRepository <Animal, Long> {
    List<Animal> findByStatus (AnimalStatus status);
    long countByShelterIdAndStatusNot (Long shelterId, AnimalStatus status);
}
