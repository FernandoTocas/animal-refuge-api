package com.fpsumma.psp.animalrefuge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicAnimalResponse {
    
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private String shelterCity;

}
