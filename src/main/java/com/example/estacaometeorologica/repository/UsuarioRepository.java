package com.example.estacaometeorologica.repository;

import com.example.estacaometeorologica.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByEmail(String email);
}
