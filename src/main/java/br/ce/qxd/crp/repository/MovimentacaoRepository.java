package br.ce.qxd.crp.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ce.qxd.crp.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{
	
	List<Movimentacao> findByTipo(char tipo, Sort sort);
	
}
