package com.fpsumma.psp.animalrefuge.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private Integer capacity;

    @Column(name = "create_ad")
    private String createdAt;

    @OneToMany
    private List<Animal> animals;
    
}
