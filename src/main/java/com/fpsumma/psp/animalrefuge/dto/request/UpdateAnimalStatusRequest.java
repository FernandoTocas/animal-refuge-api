package com.fpsumma.psp.animalrefuge.dto.request;

import com.fpsumma.psp.animalrefuge.model.AnimalStatus;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnimalStatusRequest {
    
    @NotNull
    private AnimalStatus status;
}
