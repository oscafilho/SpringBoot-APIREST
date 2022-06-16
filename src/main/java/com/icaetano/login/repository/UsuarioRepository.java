package com.icaetano.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icaetano.login.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByEmail(String email);
	
	boolean existsByEmail(String email);

}
