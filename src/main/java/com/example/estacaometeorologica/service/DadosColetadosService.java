package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.repository.DadosColetadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DadosColetadosService {

    @Autowired
    private DadosColetadosRepository dadosColetadosRepository;

    public List<DadosColetados> getDadosColetados(){
        return dadosColetadosRepository.findAllByOrderByDataDesc();
    }

    public void saveDadosColetados(DadosColetados dadosColetados){
        dadosColetadosRepository.save(dadosColetados);
    }

    public Page<DadosColetados> getDadosColetadosPorData(LocalDateTime data_inicial, LocalDateTime data_final, Pageable paginacao) {
        return dadosColetadosRepository.findByDataBetweenOrderByDataDesc(data_inicial, data_final, paginacao);
    }

    public List<DadosColetados> getDadosColetadosRecentes() {
        Pageable paginacao = PageRequest.of(0, 10);
        return dadosColetadosRepository.findAllByOrderByDataDesc(paginacao);
    }
}
