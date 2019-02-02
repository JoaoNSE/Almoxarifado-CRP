package br.ce.qxd.crp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ce.qxd.crp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByNome(String nome);
	
	public List<Usuario> findByNomeAndSenha(String nome, String senha);
	
}
