package com.fpsumma.psp.animalrefuge.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*

id
name
city
capacity
createdAt
animals

*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private Integer capacity;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "shelter")
    private List<Animal> animals;
    
}
