package com.fpsumma.psp.animalrefuge.mapper;

import com.fpsumma.psp.animalrefuge.dto.response.AnimalResponse;
import com.fpsumma.psp.animalrefuge.dto.response.CarePlanSummaryResponse;
import com.fpsumma.psp.animalrefuge.dto.response.PublicAnimalResponse;
import com.fpsumma.psp.animalrefuge.dto.response.ShelterSummaryResponse;
import com.fpsumma.psp.animalrefuge.model.Animal;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AnimalMapper {

    public PublicAnimalResponse toPublicResponse(Animal animal) {
        return new PublicAnimalResponse(
            animal.getId(),
            animal.getName(),
            animal.getSpecies(),
            animal.getBreed(),
            animal.getAge(),
            animal.getShelter().getCity()
        );
    }

    public AnimalResponse toResponse(Animal animal) {
        ShelterSummaryResponse shelter = new ShelterSummaryResponse(
            animal.getShelter().getId(),
            animal.getShelter().getName(),
            animal.getShelter().getCity()
        );

        List<CarePlanSummaryResponse> carePlans = animal.getCarePlans()
            .stream()
            .map(cp -> new CarePlanSummaryResponse(cp.getId(), cp.getName(), cp.getFrequency()))
            .toList();

        return new AnimalResponse(
            animal.getId(),
            animal.getName(),
            animal.getSpecies(),
            animal.getBreed(),
            animal.getAge(),
            animal.getStatus(),
            animal.getRegisteredBy(),
            animal.getArrivalDate(),
            shelter,carePlans
        );
    }
}