package com.example.estacaometeorologica.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<ErroDeFormDto> handle(HttpMessageNotReadableException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();

        if(exception.toString().indexOf("Required request body is missing") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("", "Corpo da requisição não encontrado"));
            return erroDeFormDtos;
        }
        if(exception.getCause().toString().indexOf("DateTimeParseException") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data", "Formato de data inválido. Utilize o formato yyyy-MM-dd HH:mm:ss"));
        }
        if(exception.getCause().toString().indexOf("JsonParseException") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("","JSON enviado em formato inválido"));
        }
        return erroDeFormDtos;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();

        if(exception.getMessage().indexOf("NotNull.dadosColetadosForm.data") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data", "Campo não preenchido"));
        }

        if(exception.getMessage().indexOf("Insira precipitação") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("precipitação", "Campo não preenchido"));
        }
        if(exception.getMessage().indexOf("Insira velocidade_vento") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("velocidade_vento", "Campo não preenchido"));
        }
        if(exception.getMessage().indexOf("Insira direcao_vento") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("direcao_vento", "Campo não preenchido"));
        }
        if(exception.getMessage().indexOf("Insira temperatura") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("temperatura", "Campo não preenchido"));
        }
        if(exception.getMessage().indexOf("Insira umidade_ar") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("umidade_ar", "Campo não preenchido"));
        }
        if(exception.getMessage().indexOf("Insira pressao_atmosferica") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("pressao_atmosferica", "Campo não preenchido"));
        }
        return erroDeFormDtos;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public List<ErroDeFormDto> handle(MissingServletRequestParameterException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();
        System.out.println(exception);
        if(exception.getMessage().indexOf("'data_inicial' is not present") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data_inicial", "Campo não preenchido"));
        }
        if(exception.getMessage().indexOf("'data_final' is not present") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data_final", "Campo não preenchido"));
        }
        return erroDeFormDtos;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public List<ErroDeFormDto> handle(MethodArgumentTypeMismatchException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();
        if(exception.getMessage().indexOf("java.time.LocalDateTime") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data", "Formato de data inválido. Utilize o formato yyyy-MM-dd HH:mm:ss"));
        }
        return erroDeFormDtos;
    }
}
