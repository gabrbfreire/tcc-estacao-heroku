package com.example.estacaometeorologica.repository;

import com.example.estacaometeorologica.model.DadosColetados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DadosColetadosRepository extends MongoRepository<DadosColetados, String> {

    Page<DadosColetados> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<DadosColetados> findAllByOrderByDataDesc(Pageable pageable);
}
