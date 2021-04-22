package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.controller.form.UsuarioSigninForm;
import com.example.estacaometeorologica.model.Usuario;
import com.example.estacaometeorologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public String registrarUsuario(UsuarioSigninForm usuarioSigninForm) {
        if(findUsuarioByEmail(usuarioSigninForm.getEmail()) == null){
            Usuario usuario = new Usuario(
                    usuarioSigninForm.getNome(),
                    usuarioSigninForm.getEmail(),
                    new BCryptPasswordEncoder().encode(usuarioSigninForm.getSenha()),
                    usuarioSigninForm.getImagem());
            usuarioRepository.save(usuario);
            return "";
        }else{
            return "Usuário com esse E-mail já existe";
        }
    }
}
