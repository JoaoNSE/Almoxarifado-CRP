package br.ce.qxd.crp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ce.qxd.crp.exception.UserAlreadyExistsException;
import br.ce.qxd.crp.model.Usuario;
import br.ce.qxd.crp.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Bean
	private BCryptPasswordEncoder bCryptEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	public void save(Usuario usuario) {
		usuario.setSenha(bCryptEncoder().encode(usuario.getSenha()));
		usuarioRepo.save(usuario);
	}
	
	public Usuario findByNome(String nome) {
		return usuarioRepo.findByNome(nome);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}
	
	public Usuario findOne(Integer id) {
		return usuarioRepo.findOne(id);
	}
	
	public void delete(Usuario usuario) {
		usuarioRepo.delete(usuario);
	}
	
	public void addUpdateUsuario(Usuario usuario) {
		Usuario tempUsuario = usuarioRepo.findByNome(usuario.getNome());
		
		if (tempUsuario != null) {
			if (usuario.getId() == null) {
				throw new UserAlreadyExistsException();
			} else if (usuario.getId().equals(tempUsuario.getId())) {
				throw new BadCredentialsException("Usuário que se deseja alterar não existe.");
			}
		}
		
		String senhaCod = bCryptEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCod);
		
		usuarioRepo.save(usuario);
	}
}
