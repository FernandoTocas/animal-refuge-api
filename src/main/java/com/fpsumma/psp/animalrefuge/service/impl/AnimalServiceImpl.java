package com.fpsumma.psp.animalrefuge.service.impl;

import com.fpsumma.psp.animalrefuge.dto.request.CreateAnimalRequest;
import com.fpsumma.psp.animalrefuge.dto.request.UpdateAnimalStatusRequest;
import com.fpsumma.psp.animalrefuge.dto.response.AnimalResponse;
import com.fpsumma.psp.animalrefuge.dto.response.PublicAnimalResponse;
import com.fpsumma.psp.animalrefuge.exception.ConflictException;
import com.fpsumma.psp.animalrefuge.exception.NotFoundException;
import com.fpsumma.psp.animalrefuge.mapper.AnimalMapper;
import com.fpsumma.psp.animalrefuge.model.Animal;
import com.fpsumma.psp.animalrefuge.model.AnimalStatus;
import com.fpsumma.psp.animalrefuge.model.CarePlan;
import com.fpsumma.psp.animalrefuge.model.Shelter;
import com.fpsumma.psp.animalrefuge.repository.AnimalRepository;
import com.fpsumma.psp.animalrefuge.repository.CarePlanRepository;
import com.fpsumma.psp.animalrefuge.repository.ShelterRepository;
import com.fpsumma.psp.animalrefuge.service.AnimalService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final ShelterRepository shelterRepository;
    private final CarePlanRepository carePlanRepository;
    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository,
                             ShelterRepository shelterRepository,
                             CarePlanRepository carePlanRepository,
                             AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.shelterRepository = shelterRepository;
        this.carePlanRepository = carePlanRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    public List<PublicAnimalResponse> listarAnimalesPublicos() {
        return animalRepository.findByStatus(AnimalStatus.AVAILABLE)
            .stream()
            .map(animalMapper::toPublicResponse)
            .toList();
    }

    @Override
    public AnimalResponse crearAnimal(CreateAnimalRequest request, String username) {
        Shelter shelter = shelterRepository.findById(request.getShelterId())
            .orElseThrow(() -> new NotFoundException("Refugio no encontrado"));

        long ocupacion = animalRepository
            .countByShelterIdAndStatusNot(shelter.getId(), AnimalStatus.ADOPTED);

        if (ocupacion >= shelter.getCapacity()) {
            throw new ConflictException("El refugio ha alcanzado su capacidad máxima");
        }

        Animal animal = new Animal();
        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setBreed(request.getBreed());
        animal.setAge(request.getAge());
        animal.setStatus(AnimalStatus.AVAILABLE);
        animal.setArrivalDate(LocalDateTime.now());
        animal.setRegisteredBy(username);
        animal.setShelter(shelter);
        animal.setCarePlans(new ArrayList<>());

        return animalMapper.toResponse(animalRepository.save(animal));
    }

    @Override
    public AnimalResponse buscarPorId(Long id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Animal no encontrado"));
        return animalMapper.toResponse(animal);
    }

    @Override
    public AnimalResponse asignarPlanCuidado(Long animalId, Long carePlanId) {
        Animal animal = animalRepository.findById(animalId)
            .orElseThrow(() -> new NotFoundException("Animal no encontrado"));

        CarePlan carePlan = carePlanRepository.findById(carePlanId)
            .orElseThrow(() -> new NotFoundException("Plan de cuidado no encontrado"));

        if (animal.getStatus() == AnimalStatus.ADOPTED) {
            throw new ConflictException("No se pueden asignar planes a animales adoptados");
        }
        if (animal.getStatus() == AnimalStatus.DECEASED) {
            throw new ConflictException("No se pueden asignar planes a animales fallecidos");
        }
        if (animal.getCarePlans().contains(carePlan)) {
            throw new ConflictException("El plan de cuidado ya está asignado a este animal");
        }

        animal.getCarePlans().add(carePlan);
        return animalMapper.toResponse(animalRepository.save(animal));
    }

    @Override
    public AnimalResponse actualizarEstado(Long id, UpdateAnimalStatusRequest request) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Animal no encontrado"));

        AnimalStatus actual = animal.getStatus();
        AnimalStatus nuevo = request.getStatus();

        if (actual == AnimalStatus.DECEASED) {
            throw new ConflictException("No se puede cambiar el estado de un animal fallecido");
        }
        if (actual == AnimalStatus.ADOPTED && nuevo == AnimalStatus.AVAILABLE) {
            throw new ConflictException("Un animal adoptado no puede volver a AVAILABLE");
        }
        if (actual == AnimalStatus.UNDER_TREATMENT && nuevo == AnimalStatus.ADOPTED) {
            throw new ConflictException("Un animal en tratamiento no puede pasar directamente a ADOPTED");
        }

        animal.setStatus(nuevo);
        return animalMapper.toResponse(animalRepository.save(animal));
    }
}