package com.fpsumma.psp.animalrefuge.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fpsumma.psp.animalrefuge.model.AnimalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalResponse {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private AnimalStatus status;
    private String registeredBy;
    private LocalDateTime arrivalDate;
    private ShelterSummaryResponse shelter;
    private List<CarePlanSummaryResponse> carePlans;
    
}
