package com.fpsumma.psp.animalrefuge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarePlanSummaryResponse {
    
    private Long id;
    private String name;
    private String frequency;
}
