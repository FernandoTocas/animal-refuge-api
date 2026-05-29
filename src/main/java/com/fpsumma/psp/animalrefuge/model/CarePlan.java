package com.fpsumma.psp.animalrefuge.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "care_plans")
public class CarePlan {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String frequency;
    
    @ManyToMany(mappedBy = "carePlans")
    private List<Animal> animals;

}
