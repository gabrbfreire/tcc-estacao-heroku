package com.example.estacaometeorologica.repository;

import com.example.estacaometeorologica.model.DadosColetados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DadosColetadosRepository extends MongoRepository<DadosColetados, String> {

    Page<DadosColetados> findByDataBetweenOrderByDataDesc(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<DadosColetados> findByDataBetweenOrderByDataDesc(LocalDateTime startDate, LocalDateTime endDate);

    List<DadosColetados> findAllByOrderByDataDesc(Pageable pageable);

    List<DadosColetados> findAllByOrderByDataDesc();

    DadosColetados findFirstByOrderByDataDesc();

    List<DadosColetados> findFirst2ByOrderByDataDesc();
}
