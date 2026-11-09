package com.example.seriesapi.service;

import com.example.seriesapi.models.Serie;
import com.example.seriesapi.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> listarSeries() {
        return serieRepository.findAll();
    }

    public Optional<Serie> buscarSerie(Long id) {
        return serieRepository.findById(id);
    }

    public Serie guardarSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    public void eliminarSerie(Long id) {
        serieRepository.deleteById(id);
    }
}