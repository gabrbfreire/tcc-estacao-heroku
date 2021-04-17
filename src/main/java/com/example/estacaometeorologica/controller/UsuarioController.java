package com.example.estacaometeorologica.controller;

import com.example.estacaometeorologica.config.validation.ErroDeFormDto;
import com.example.estacaometeorologica.controller.dto.TokenDto;
import com.example.estacaometeorologica.controller.form.UsuarioLoginForm;
import com.example.estacaometeorologica.controller.form.UsuarioSigninForm;
import com.example.estacaometeorologica.service.TokenService;
import com.example.estacaometeorologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("login")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid UsuarioLoginForm usuarioLoginForm){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioLoginForm.getEmail(), usuarioLoginForm.getSenha()));
            String token = tokenService.gerarToken(authentication);

            return new ResponseEntity<>(new TokenDto(token, "Bearer"), HttpStatus.OK);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<ErroDeFormDto> registrar(@RequestBody @Valid UsuarioSigninForm usuarioSigninForm){
        String resultadoRegistro = usuarioService.registrarUsuario(usuarioSigninForm);
        if(resultadoRegistro == ""){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ErroDeFormDto("email", resultadoRegistro),HttpStatus.BAD_REQUEST);
        }
    }
}