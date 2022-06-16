package com.icaetano.login.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.icaetano.login.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	
	public UsuarioDTO(Usuario usu) {
		this.id = usu.getId();
		this.nome = usu.getNome();
		this.email = usu.getEmail();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	
	public static List<UsuarioDTO> converter(List<Usuario> usuarios) {
		
		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
}
