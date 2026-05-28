package com.fpsumma.psp.animalrefuge.service;

import java.util.List;

import com.fpsumma.psp.animalrefuge.dto.request.CreateAnimalRequest;
import com.fpsumma.psp.animalrefuge.dto.request.UpdateAnimalStatusRequest;
import com.fpsumma.psp.animalrefuge.dto.response.AnimalResponse;
import com.fpsumma.psp.animalrefuge.dto.response.PublicAnimalResponse;

public interface AnimalService {

    List<PublicAnimalResponse> listarAnimalesPublicos();
    AnimalResponse crearAnimal(CreateAnimalRequest request, String username);
    AnimalResponse buscarPorId(Long id);
    AnimalResponse asignarPlanCuidado (Long animalId, Long carePlanId);
    AnimalResponse actualizarEstado (Long id, UpdateAnimalStatusRequest request);
}
