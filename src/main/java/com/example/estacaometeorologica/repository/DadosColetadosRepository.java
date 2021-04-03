package com.example.estacaometeorologica.repository;

import com.example.estacaometeorologica.model.DadosColetados;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DadosColetadosRepository extends MongoRepository<DadosColetados, String> {

    List<DadosColetados> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
}
