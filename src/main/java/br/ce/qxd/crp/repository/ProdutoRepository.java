package br.ce.qxd.crp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ce.qxd.crp.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}