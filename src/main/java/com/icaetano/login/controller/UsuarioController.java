package com.icaetano.login.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.icaetano.login.dto.UsuarioDTO;
import com.icaetano.login.form.UsuarioUpdateForm;
import com.icaetano.login.form.UsuarioViewForm;
import com.icaetano.login.model.Usuario;
import com.icaetano.login.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioViewForm form,
			UriComponentsBuilder uriBuilder) {

		Usuario usuario = form.convert();
		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new RuntimeException("Email já cadastrado");
		}
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateForm form) {

		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if (optUsuario.isPresent()) {
			Usuario usuario = form.atualiza(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDTO(usuario));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if (optUsuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/lista/{id}")
	public ResponseEntity<UsuarioDTO> lista(@PathVariable Long id) {

		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if (optUsuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(optUsuario.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/listaTodos")
	public List<UsuarioDTO> listaAll() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return UsuarioDTO.converter(usuarios);

	}
}
