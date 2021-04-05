package com.example.estacaometeorologica.controller;

import com.example.estacaometeorologica.controller.dto.DadosColetadosPaginadosDto;
import com.example.estacaometeorologica.controller.form.DadosColetadosForm;
import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.service.DadosColetadosService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DadosController {

    @Autowired
    private DadosColetadosService dadosColetadosService;

    @GetMapping("dados-coletados")
    @Cacheable(value = "dados-coletados")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<DadosColetados>> getDadosColetados(){
        return new ResponseEntity<>(dadosColetadosService.getDadosColetados(), HttpStatus.OK);
    }

    @GetMapping("dados-coletados-por-data")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<DadosColetadosPaginadosDto> getDadosColetadosPorData(
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
                                                                         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                                         @NotNull
                                                                         LocalDateTime data_inicial,
                                                                         @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
                                                                         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                                         @NotNull
                                                                         LocalDateTime data_final,
                                                                         @RequestParam
                                                                         @NotNull
                                                                         @Min(1)
                                                                         Integer pagina,
                                                                         @RequestParam
                                                                         @NotNull
                                                                         @Min(1)
                                                                         Integer quantidade){
        Pageable paginacao = PageRequest.of(pagina-1, quantidade);
        Page<DadosColetados> itemsPaginados = dadosColetadosService.getDadosColetadosPorData(data_inicial, data_final, paginacao);

        long itemInicialPagina = pagina * itemsPaginados.getNumberOfElements() - itemsPaginados.getNumberOfElements() + 1;
        int itemFinalPagina = pagina * itemsPaginados.getNumberOfElements();

        DadosColetadosPaginadosDto dadosColetadosPaginadosDto = new DadosColetadosPaginadosDto(itemsPaginados.getContent(),
                "Mostrando de "+itemInicialPagina +" a "+ itemFinalPagina + " de " + itemsPaginados.getTotalElements() +" registros");
        return ResponseEntity.ok().body(dadosColetadosPaginadosDto);
    }

    @GetMapping("dados-recentes")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<DadosColetados>> getDadosColetadosRecentes(@RequestParam
                                                                                      @Min(1)
                                                                                      @NotNull
                                                                                  Integer quantidadeDeDados){
        Pageable paginacao = PageRequest.of(0, quantidadeDeDados);
        return new ResponseEntity(dadosColetadosService.getDadosColetadosRecentes(paginacao), HttpStatus.OK);
    }

    @PostMapping("dados-coletados")
    @CacheEvict(value = "dados-coletados", allEntries = true)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> saveDadosColetados(@RequestBody @Valid DadosColetadosForm dadosColetadosForm){
        dadosColetadosService.saveDadosColetados(dadosColetadosForm.converter());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
