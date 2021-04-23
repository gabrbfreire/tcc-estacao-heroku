package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.controller.dto.DadosUsuarioDto;
import com.example.estacaometeorologica.controller.form.ImagemUsuarioForm;
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
            return "";
        }else{
            return "Usuário com esse E-mail já existe";
        }
    }

    public DadosUsuarioDto getDadosUsuario(String email){
        Usuario usuario = findUsuarioByEmail(email);
        return new DadosUsuarioDto(usuario.getNome(), usuario.getImagem());
    }

    public void alterarImagem(String imagem, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        usuario.setImagem(imagem);
        usuarioRepository.save(usuario);
    }
}
