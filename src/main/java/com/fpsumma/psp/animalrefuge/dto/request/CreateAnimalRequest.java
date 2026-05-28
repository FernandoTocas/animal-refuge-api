package com.fpsumma.psp.animalrefuge.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnimalRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String species;
    
    private String breed;

    @Min(0)
    private Integer age;

    @NotNull
    private Long shelterId;
    
}
