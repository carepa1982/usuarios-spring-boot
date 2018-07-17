package com.usuario.api.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.usuario.api.dao.UsuarioRepository;
import com.usuario.api.model.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void persist() {
		// given
		Usuario usuario = new Usuario();
		usuario.setPrimerNombre("Diego Nilton");
		usuario.setSegundoNombre("Castaño");
		usuario.setLogin("Dcastant");
		usuario.setEmail("nilton-08@hotmail.com");
		entityManager.persistAndFlush(usuario);
		// when
		List<Usuario> usuarios = usuarioRepository.findAll();
		//then
		assertTrue(!usuarios.isEmpty());
		//when
		boolean existeUsuario = usuarios.stream().anyMatch(a -> a.getLogin().equals("Dcastant"));
		//then
		assertTrue(existeUsuario);
	}
	
	@Test
	public void validateListNotEmptyRepository() {
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		assertThat(usuarios).isNotEmpty();
	}
	
	@Test
	public void deleteAllUsers() {
		entityManager.persistAndFlush(new Usuario(null, "Jack", "Smith","dcastant","nilton-08@hotmail.com"));
		entityManager.persistAndFlush(new Usuario(null, "Diego", "toro","dcastant","diego-08@hotmail.com"));
		usuarioRepository.deleteAll();
		assertThat(usuarioRepository.findAll()).isEmpty();
	}
	
	@Test
	public void findUserById() {
		Usuario usuario1 = new Usuario(null, "Diana", "Pelaez","diana.pelaez","nilton-08@hotmail.com");
		entityManager.persist(usuario1);
		Usuario usuario2 = new Usuario(null, "Diego", "toro","dcastant","diego-08@hotmail.com");
		entityManager.persist(usuario2);
		Usuario usuarios = usuarioRepository.findByCodigo(usuario2.getCodigo());
		assertThat(usuarios).isEqualTo(usuario2);
	}

}
