package com.example.estacaometeorologica.config.security;

import com.example.estacaometeorologica.repository.UsuarioRepository;
import com.example.estacaometeorologica.service.AutenticacaoService;
import com.example.estacaometeorologica.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login", "/sign-in").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
