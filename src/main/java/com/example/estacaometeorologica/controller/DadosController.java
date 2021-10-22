package com.example.estacaometeorologica.controller;

import com.example.estacaometeorologica.config.validation.ErroDeFormDto;
import com.example.estacaometeorologica.controller.dto.DadosColetadosDto;
import com.example.estacaometeorologica.controller.dto.DadosColetadosPaginadosDto;
import com.example.estacaometeorologica.controller.dto.DadosColetadosRecentesDto;
import com.example.estacaometeorologica.controller.form.DadosColetadosForm;
import com.example.estacaometeorologica.model.DadosColetados;
import com.example.estacaometeorologica.service.DadosColetadosService;
import com.example.estacaometeorologica.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class DadosController {

    @Autowired
    private DadosColetadosService dadosColetadosService;

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("dados-coletados")
    public ResponseEntity<DadosColetadosDto> getDadosColetados(){
        DadosColetadosDto dadosColetadosDto = new DadosColetadosDto(dadosColetadosService.getDadosColetados());
        return new ResponseEntity<>(dadosColetadosDto, HttpStatus.OK);
    }

    @GetMapping("dados-coletados-por-data")
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
                                                                         Integer quantidade) {
        Pageable paginacao = PageRequest.of(pagina-1, quantidade);
        Page<DadosColetados> itemsPaginados = dadosColetadosService.getDadosColetadosPorData(data_inicial, data_final, paginacao);

        long itemInicialPagina = pagina * quantidade - quantidade + 1;
        int itemFinalPagina = pagina * quantidade;
        if(itemFinalPagina > itemsPaginados.getTotalElements()){
            itemFinalPagina = (int) itemsPaginados.getTotalElements();
        }

        DadosColetadosPaginadosDto dadosColetadosPaginadosDto = new DadosColetadosPaginadosDto(
                itemsPaginados.getContent(),
                "Mostrando de "+ itemInicialPagina +" a "+ itemFinalPagina + " de " + itemsPaginados.getTotalElements() +" registros",
                itemsPaginados.getTotalPages()
        );
        return new ResponseEntity<>(dadosColetadosPaginadosDto, HttpStatus.OK);
    }

    @GetMapping("dados-recentes")
    public ResponseEntity<DadosColetadosRecentesDto> getDadosColetadosRecentes() {
        DadosColetadosRecentesDto dadosColetadosRecentesDto = new DadosColetadosRecentesDto(dadosColetadosService.getDadosColetadosRecentes());
        return new ResponseEntity(dadosColetadosRecentesDto, HttpStatus.OK);
    }

    @PostMapping("dados-coletados")
    public ResponseEntity<ErroDeFormDto> saveDadosColetados(@RequestBody Map<String, Object> dadosColetados, @RequestHeader String auth) {
        if(auth.equals("2q6VYU4vzsWWPX7avFdrVYTxOs0fwqP9")){
            dadosColetadosService.saveDadosColetados(dadosColetados);
            template.convertAndSend("/topic/ultimos-registros", dadosColetadosService.getDadoColetadoInseridoMaisRecente());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ErroDeFormDto("auth","Token inválido"), HttpStatus.BAD_REQUEST);
    }
}