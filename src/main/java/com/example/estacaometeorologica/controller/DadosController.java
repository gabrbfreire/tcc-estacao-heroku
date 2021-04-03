package com.example.estacaometeorologica.controller;

import com.example.estacaometeorologica.controller.form.DadosColetadosForm;
import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.service.DadosColetadosService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DadosController {

    @Autowired
    private DadosColetadosService dadosColetadosService;

    @GetMapping("dados-coletados")
    @Cacheable(value = "dados-coletados")
    public ResponseEntity<List<DadosColetados>> getDadosColetados(){
        return new ResponseEntity<>(dadosColetadosService.getDadosColetados(), HttpStatus.OK);
    }

    @GetMapping("dados-coletados-por-data")
    public ResponseEntity<List<DadosColetados>> getDadosColetadosPorData(@RequestParam
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
                                                                             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                                             @NotNull
                                                                             LocalDateTime data_inicial,
                                                                             @RequestParam
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
                                                                             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                                             @NotNull
                                                                             LocalDateTime data_final){
        return ResponseEntity.ok().body(dadosColetadosService.getDadosColetadosPorData(data_inicial, data_final));
    }

    @PostMapping("dados-coletados")
    @CacheEvict(value = "dados-coletados", allEntries = true)
    public ResponseEntity<Object> saveDadosColetados(@RequestBody @Valid DadosColetadosForm dadosColetadosForm){
        dadosColetadosService.saveDadosColetados(dadosColetadosForm.converter());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
