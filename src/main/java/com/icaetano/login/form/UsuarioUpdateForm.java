package com.icaetano.login.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icaetano.login.model.Usuario;
import com.icaetano.login.repository.UsuarioRepository;

public class UsuarioUpdateForm {

	
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario atualiza(Long id, UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.findById(id).get();
		usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
		
		return usuario;
		
	}
	
}
