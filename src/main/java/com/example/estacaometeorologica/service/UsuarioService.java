package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.controller.dto.DadosUsuarioDto;
import com.example.estacaometeorologica.controller.form.UsuarioSigninForm;
import com.example.estacaometeorologica.model.Usuario;
import com.example.estacaometeorologica.repository.UsuarioRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailRemetente;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AuthenticationManager authenticationManager;

    public Usuario findUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public void registrarUsuario(UsuarioSigninForm usuarioSigninForm) throws MessagingException, UnsupportedEncodingException {
        if(findUsuarioByEmail(usuarioSigninForm.getEmail()) == null){

            // Imagem default
            if(usuarioSigninForm.getImagem() == null){
                usuarioSigninForm.setImagem("iVBORw0KGgoAAAANSUhEUgAAAJYAAACWCAYAAAA8AXHiAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAROSURBVHhe7d1NTlwxEMRxkhsgJE7Ahitwf7gCG07AhiMkaeUNihBfAZfdVf7/NrzZRDCu1277TcY/bm9vf50Bg/08fgJDESxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxIECxI8KUgh5ubm+Pq++7u7o6rfVGx/ri6ujquxqh/7/z8/Hi1p60qVg345eXl8WqNXarZNhWrprrVoSojp9zO4itWTUnX19fHq16Sq1d8xeoaqnSRFatDL/U/EitXZMVyClUZvSrtIC5Yjs1x3QhpTT37WI0khSsmWLX6SxiYlHBFBKt6FFZ/vUQEy61Z/0hVLfdHQvbBSn0m516B7YOVPAU6b0NYByttif6S8zaEbbASNxWTWAar7uK0hv09jlXLLlhUKg92wdqpUv3LbfVr3bzv5OLi4rjyQLBMVKV2qloEy4jTnp1VsFJ32RPZBIsHzV6YCiFhE6xdtxlectkspWJBgmBBgmBBgmBBgmBBgmBBgmBBgmBBgmBBgmBBgmBBgmBBgmBBwiZYj4+Px9XeXL79zyZYDw8PhMuI1VRY4YIHeiwjThWbYBlxqtgEy8T9/f1x5YFgmXh6ejquPNgFiyPbPFhWLLdpYUeWwappYadwOVZp2x7Lref4KtdNYZr35lw3hSNO/0r8ktuqVM5PGqhYDVX/6P74KiJYaY18Qv8YdxCm+7SYsk/HVNhI0uZvXLBcBydtOqdiNVChStuXizxs/KS+XrL7F7a5byu8Jbpi1YB1nmJq2k4MVYmfCmuK6dZ3VdiTGvXXbNNj1UB2eO5Wv8cOzzmje6yTjntbVCxzXU8LSz/FLK5iOawEX5O2OowIVh2FknRqRcI0GTEVchRKP9YVy3Xa+yzn6dG6YqUfg1J/n+uJZ3YVK/HTop/h1nfZVKy6c9OX6O+pG8rpprIJVp2JnD71JbEJFqH6y6VqWQRr177qLfV+dG8LWjfvBOpjXZv6thWLg8U/p+v71K5iUaW+pttmqk3zjvd1W9y0CtbO+1QjdKr2bYJVbwpbCt/XJVwtgkWlytMiWFSqsTpUreXBYltBY3W4lgar/ng+pKez8qZt07xjvJU37bJg0bDPsep9XhYsGvY56n1eEa4lwaJazbXiJp4erGooqVb5pgarQsUqcI1agc/cgpgarPp4MfYwLVh1tzAF7mNJ8451Zm2aTgkWj236qB53xnhMCRa9VS8zxmNKsOitepkxHvJgrX7Kjtepx4XmHRIECxLSYLEa7E05PtJg8fimN+X4yILFJxg8qMZJFiy2GDyoxonmHRIECxIEC5LVIcGCZHVIsCAhCRZbDZAEi60GDA8W1crT6HEbHiyqlafR40bzDgmCBQmChWcj+yyChWcj+6yhweKDfTgZGiz+mxdOhgaLrQac0GNB4OzsNxeV/vKXRCGxAAAAAElFTkSuQmCC");
            }

            Usuario usuario = new Usuario(
                    usuarioSigninForm.getNome(),
                    usuarioSigninForm.getEmail(),
                    new BCryptPasswordEncoder().encode(usuarioSigninForm.getSenha()),
                    usuarioSigninForm.getImagem());
            usuarioRepository.save(usuario);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(emailRemetente, "Estação Meteorológica");
            helper.setTo(usuarioSigninForm.getEmail());
            helper.setSubject("Confirmar criação de conta Santa Clima");
            helper.setText("Clique no link abaixo para confirmar a criação de sua conta no Santa Clima<br><br>" +
                    "<a href=\"https://tcc-estacao-meteorologica.herokuapp.com/confirmar-criacao-conta target=\\\"_blank\\\">Confirmar</a><br><br>", true);
            mailSender.send(message);
        }else{
            throw new InvalidParameterException();
        }
    }

    public DadosUsuarioDto getDadosUsuario(String email){
        Usuario usuario = findUsuarioByEmail(email);
        return new DadosUsuarioDto(usuario.getNome());
    }

    public void alterarImagem(String imagem, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        usuario.setImagem(imagem);
        usuarioRepository.save(usuario);
    }

    public void requisitarResetSenha(String email) throws UnsupportedEncodingException, MessagingException {
        Usuario usuario = findUsuarioByEmail(email);

        if(usuario != null){
            String codigo = RandomStringUtils.randomAlphabetic(10);
            usuario.setCodigo_reset_senha(codigo);
            usuarioRepository.save(usuario);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(emailRemetente, "Estação Meteorológica");
            helper.setTo(email);
            helper.setSubject("Confirmação de Reset de Senha");
            helper.setText("<b>Se você não requisitou o reset de sua senha ignore esse e-mail</b><br><br>" +
                    "Se você requisitou o reset de sua senha clique no link abaixo para prosseguir:<br><br>" +
                    "<a href=\"https://tcc-estacao-meteorologica.herokuapp.com/confirmar-reset-senha?id="+ usuario.getId() +"&codigo="+ codigo +"\" target=\\\"_blank\\\">Confirmar</a><br><br>" +
                    "Esse link só irá funcionar uma única vez, para fazer o reset de senha novamente será necessário requisitar outro reset", true);
            mailSender.send(message);
        }
    }

    public void confirmarResetSenha(String id, String codigo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            String codigoDb = usuario.getCodigo_reset_senha();
            if(codigoDb.equals(codigo)){
                resetarSenha(usuario);
                usuario.setCodigo_reset_senha("");
                usuarioRepository.save(usuario);
            }else{
                throw new InvalidParameterException();
            }
        }else{
            throw new InvalidParameterException();
        }
    }

    private void resetarSenha(Usuario usuario) {
        String novaSenha = RandomStringUtils.randomAlphabetic(10);
        usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        usuarioRepository.save(usuario);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(usuario.getEmail());
        simpleMailMessage.setFrom(emailRemetente);
        simpleMailMessage.setSubject("Reset de senha");
        simpleMailMessage.setText("Sua senha de acesso foi resetada. \n\nSua nova senha é: "+novaSenha);
        javaMailSender.send(simpleMailMessage);
    }

    public void alterarSenha(String atual_senha, String nova_senha, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, atual_senha));

        if(atual_senha.equals(nova_senha)){
            throw new InvalidParameterException();
        }

        usuario.setSenha(new BCryptPasswordEncoder().encode(nova_senha));
        usuarioRepository.save(usuario);
    }

    public String obterImagemUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario.getImagem();
    }
}
