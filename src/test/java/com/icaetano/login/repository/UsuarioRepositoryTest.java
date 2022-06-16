package com.icaetano.login.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icaetano.login.model.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void testarBuscaEmailValido() {
		String email = "osvaldo@icaetano.com";
		
		Usuario usuario = usuarioRepository.findByEmail(email).get();
		Assert.assertNotNull(usuario);
		Assert.assertEquals(email, usuario.getEmail());
		
	}

	@Test
	public void testarBuscaEmailInvalido() {
		String email = "osvaldo@icaetano.com";
		
		Usuario usuario = usuarioRepository.findByEmail(email).get();
		Assert.assertEquals("Email não localizado!",email, usuario.getEmail());
		
	}
	
}
