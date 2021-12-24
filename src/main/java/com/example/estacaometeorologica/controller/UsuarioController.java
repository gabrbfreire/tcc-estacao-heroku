package com.example.estacaometeorologica.controller;

import com.example.estacaometeorologica.config.validation.ErroDeFormDto;
import com.example.estacaometeorologica.controller.dto.DadosUsuarioDto;
import com.example.estacaometeorologica.controller.dto.ImagemUsuarioDto;
import com.example.estacaometeorologica.controller.form.*;
import com.example.estacaometeorologica.service.TokenService;
import com.example.estacaometeorologica.service.UsuarioService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidParameterException;

@RestController
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("login")
    public ResponseEntity<Object> autenticar(@RequestBody @Valid UsuarioLoginForm usuarioLoginForm){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioLoginForm.getEmail(), usuarioLoginForm.getSenha()));
            if(usuarioService.findUsuarioByEmail(usuarioLoginForm.getEmail()).isHabilitado() == false){
                throw new InvalidParameterException();
            }
            String token = tokenService.gerarToken(authentication);

            DadosUsuarioDto dadosUsuarioDto = usuarioService.getDadosUsuario(usuarioLoginForm.getEmail());
            dadosUsuarioDto.setToken(token);
            dadosUsuarioDto.setTipo_autenticacao("Bearer");

            return new ResponseEntity<>(dadosUsuarioDto, HttpStatus.OK);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(new ErroDeFormDto("", "Email ou senha incorretos") ,HttpStatus.BAD_REQUEST);
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(new ErroDeFormDto("", "Usuario não confirmado") ,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<ErroDeFormDto> registrar(@RequestBody @Valid UsuarioSigninForm usuarioSigninForm){
        try {
            usuarioService.registrarUsuario(usuarioSigninForm);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (InvalidParameterException | MessagingException | UnsupportedEncodingException e){
            return new ResponseEntity<>(new ErroDeFormDto("email", "Usuário com esse E-mail já existe"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("alterar-imagem")
    public ResponseEntity<ImagemUsuarioDto> alterarImagem(@RequestBody @Valid ImagemUsuarioForm imagemUsuarioForm, Authentication authentication){
        try {
            usuarioService.alterarImagem(imagemUsuarioForm.getImagem(), authentication.getName());
            return new ResponseEntity<>(new ImagemUsuarioDto(imagemUsuarioForm.getImagem()), HttpStatus.OK);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("imagem")
    public ResponseEntity<ImagemUsuarioDto> obterImagemUsuario(Authentication authentication){
        try {
            return new ResponseEntity<>(new ImagemUsuarioDto(usuarioService.obterImagemUsuario(authentication.getName())), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("requisitar-reset-senha")
    public ResponseEntity<ErroDeFormDto> requisitarResetSenha(@RequestBody @Valid ResetarSenhaForm resetarSenhaForm){
        try {
            usuarioService.requisitarResetSenha(resetarSenhaForm.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(new ErroDeFormDto("email", "Usuário não existe"), HttpStatus.BAD_REQUEST);
        }catch (UnsupportedEncodingException | MessagingException e){
            return new ResponseEntity<>(new ErroDeFormDto("", "Erro no envio do e-mail"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("confirmar-reset-senha")
    public ResponseEntity<Object> confirmarResetSenha(@RequestParam
                                                             @Pattern(regexp = "^[A-Za-z0-9]+$") String id,
                                                             @RequestParam
                                                             @Pattern(regexp = "^[A-Za-z0-9]+$") String codigo){
        try {
            usuarioService.confirmarResetSenha(id, codigo);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://santa-clima.netlify.app/pos-reset-senha")).build();
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(new ErroDeFormDto("", "Esse link já foi utilizado ou o usuário não existe"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("alterar-senha")
    public ResponseEntity<Object> alterarSenha(@RequestBody @Valid AlterarSenhaForm alterarSenhaForm, Authentication authentication){
        try {
            usuarioService.alterarSenha(alterarSenhaForm.getAtual_senha(), alterarSenhaForm.getNova_senha(), authentication.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(new ErroDeFormDto("email", "Usuário não existe"), HttpStatus.BAD_REQUEST);
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(new ErroDeFormDto("nova_senha", "Nova senha é igual a senha atual"), HttpStatus.BAD_REQUEST);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(new ErroDeFormDto("atual_senha", "Senha incorreta"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("confirmar-criacao-conta/{codigo}/{id}")
    public ResponseEntity<Object> confirmarCriacaoConta(@PathVariable String codigo, @PathVariable String id){
        try {
            usuarioService.confirmarCriacaoConta(codigo, id);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://santa-clima.netlify.app/")).build();
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(new ErroDeFormDto("", "Esse link já foi utilizado ou o usuário não existe"), HttpStatus.BAD_REQUEST);
        }
    }
}
