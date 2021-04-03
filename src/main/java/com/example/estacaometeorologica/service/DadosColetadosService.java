package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.repository.DadosColetadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DadosColetadosService {

    @Autowired
    private DadosColetadosRepository dadosColetadosRepository;

    public List<DadosColetados> getDadosColetados(){

        return dadosColetadosRepository.findAll();
    }

    public void saveDadosColetados(DadosColetados dadosColetados){
        dadosColetadosRepository.save(dadosColetados);
    }

    public List<DadosColetados> getDadosColetadosPorData(LocalDateTime data_inicial, LocalDateTime data_final) {
        return dadosColetadosRepository.findByDataBetween(data_inicial, data_final);
    }
}
