package com.example.seriesapi.controller;

import com.example.seriesapi.models.Serie;
import com.example.seriesapi.service.SerieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    // Listar todas las series
    @GetMapping
    public ResponseEntity<List<Serie>> listarSeries() {
        return ResponseEntity.ok(serieService.listarSeries());
    }

    // Buscar una serie por ID
    @GetMapping("/{id}")
    public ResponseEntity<Serie> buscarSerie(@PathVariable Long id) {
        return serieService.buscarSerie(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar una nueva serie
    @PostMapping
    public ResponseEntity<Serie> crearSerie(@Valid @RequestBody Serie serie) {
        Serie nuevaSerie = serieService.guardarSerie(serie);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSerie);
    }

    // Actualizar una serie
    @PutMapping("/{id}")
    public ResponseEntity<Serie> actualizarSerie(
            @PathVariable Long id,
            @Valid @RequestBody Serie serie) {

        return serieService.buscarSerie(id)
                .map(serieExistente -> {
                    serie.setId(id);
                    return ResponseEntity.ok(
                            serieService.guardarSerie(serie)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una serie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSerie(@PathVariable Long id) {

        if (serieService.buscarSerie(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        serieService.eliminarSerie(id);
        return ResponseEntity.noContent().build();
    }
}