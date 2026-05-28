package com.fpsumma.psp.animalrefuge.controller;

import java.lang.annotation.Repeatable;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpsumma.psp.animalrefuge.dto.request.CreateAnimalRequest;
import com.fpsumma.psp.animalrefuge.dto.request.UpdateAnimalStatusRequest;
import com.fpsumma.psp.animalrefuge.dto.response.AnimalResponse;
import com.fpsumma.psp.animalrefuge.dto.response.HealthResponse;
import com.fpsumma.psp.animalrefuge.dto.response.PublicAnimalResponse;
import com.fpsumma.psp.animalrefuge.service.AnimalService;

import jakarta.validation.Valid;

@RestController
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/api/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(
            new HealthResponse("OK", "Animal Refuge API is running")
        );
    }

    @GetMapping("/api/public/animals")
    public ResponseEntity<List<PublicAnimalResponse>> listarPublico() {
        return ResponseEntity.ok(animalService.listarAnimalesPublicos());
    }

    @PostMapping("/api/animals")
    public ResponseEntity<AnimalResponse> crear(
            @Valid @RequestBody CreateAnimalRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(animalService.crearAnimal(request, userDetails.getUsername()));
    }

    @GetMapping("/api/animals/{id}")
    public ResponseEntity<AnimalResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.buscarPorId(id));
    }

    @PostMapping("/api/animals/{animalId}/care-plans/{carePlanId}")
    public ResponseEntity<AnimalResponse> asignarPlan(
            @PathVariable Long animalId,
            @PathVariable Long carePlanId) {
        return ResponseEntity.ok(animalService.asignarPlanCuidado(animalId, carePlanId));
    }

    @PatchMapping("/api/animals/{id}/status")
    public ResponseEntity<AnimalResponse> actualizarEstado(
        @PathVariable Long id,
        @Valid @RequestBody UpdateAnimalStatusRequest request) {
        return ResponseEntity.ok(animalService.actualizarEstado(id, request));
        }
}
