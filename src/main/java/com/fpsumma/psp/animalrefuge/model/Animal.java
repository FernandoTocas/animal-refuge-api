package com.fpsumma.psp.animalrefuge.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
/*
id
name
species
breed
age
status
arrivalDate
registeredBy
shelter
carePlans
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String breed;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private AnimalStatus status;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "registered_by")
    private String registeredBy;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToMany
    @JoinTable(
        name = "animal_care_plan",
        joinColumns = @JoinColumn(name = "animal_id"),
        inverseJoinColumns = @JoinColumn(name = "care_plan_id")
    )
    private List<CarePlan> carePlans;
}
