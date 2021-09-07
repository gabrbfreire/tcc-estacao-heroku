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

        String mensagem = exception.getMessage();

        if(mensagem.indexOf("Required request body is missing") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("", "Corpo da requisição não encontrado"));
            return erroDeFormDtos;
        }
        if(mensagem.toString().indexOf("DateTimeParseException") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data", "Formato de data inválido. Utilize o formato yyyy-MM-dd HH:mm:ss"));
        }
        if(mensagem.toString().indexOf("JsonParseException") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("","JSON enviado em formato inválido"));
        }
        return erroDeFormDtos;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();

        String mensagem = exception.getMessage();

        if(mensagem.indexOf("NotNull.dadosColetadosForm.data") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo precipitação") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("precipitação", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo velocidade_vento") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("velocidade_vento", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo direcao_vento") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("direcao_vento", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo temperatura") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("temperatura", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo umidade_ar") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("umidade_ar", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo pressao_atmosferica") != -1) {
            erroDeFormDtos.add(new ErroDeFormDto("pressao_atmosferica", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo email") != -1) {
            erroDeFormDtos.add(new ErroDeFormDto("email", "Campo não preenchido"));
        }
        if(mensagem.indexOf("Insira o campo nova_senha") != -1) {
            erroDeFormDtos.add(new ErroDeFormDto("nova_senha", "Campo não preenchido"));
        }
        if(mensagem.indexOf("150x150") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("imagem", "Imagem tem que ter no máximo 40kB"));
        }
        if(mensagem.indexOf("email inválido") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("email", "email inválido"));
        }
        if(mensagem.indexOf("nome inválido") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("nome", "nome inválido"));
        }
        if(mensagem.indexOf("imagem inválida") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("imagem", "imagem inválida"));
        }
        return erroDeFormDtos;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public List<ErroDeFormDto> handle(MissingServletRequestParameterException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();

        String mensagem = exception.getMessage();

        if(mensagem.indexOf("'data_inicial' is not present") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data_inicial", "Campo não preenchido"));
        }
        if(mensagem.indexOf("'data_final' is not present") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("data_final", "Campo não preenchido"));
        }
        if(mensagem.indexOf("'pagina' is not present") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("pagina", "Campo não preenchido"));
        }
        if(mensagem.indexOf("'quantidade' is not present") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("quantidade", "Campo não preenchido"));
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

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public List<ErroDeFormDto> handle(IllegalArgumentException exception){
        List<ErroDeFormDto> erroDeFormDtos = new ArrayList<>();

        String mensagem = exception.getMessage();

        if(mensagem.indexOf("index") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("pagina", "pagina não dever ser menor que 0"));
        }
        if(mensagem.indexOf("size") != -1){
            erroDeFormDtos.add(new ErroDeFormDto("quantidade", "quantidade não dever ser menor que 1"));
        }
        return erroDeFormDtos;
    }
}
