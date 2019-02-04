package br.ce.qxd.crp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.ce.qxd.crp.model.Item;



public interface ItemRepository extends JpaRepository<Item, Integer>{

	
	@Query(value = "SELECT i.produto_id, i.validade, sum(i.qtd) as qtd" + 
	" FROM movimentacao m, item i WHERE m.id = i.movimentacao_id AND qtd > 0" +
	" GROUP BY i.produto_id, i.validade", nativeQuery = true)
	List<Object[]> getEstoque();
	
	@Query(value = "SELECT i.produto_id, sum(i.qtd) as qtd" + 
	" FROM movimentacao m, item i WHERE m.id = i.movimentacao_id AND qtd > 0" +
	" GROUP BY i.produto_id", nativeQuery = true)
	List<Object[]> getEstoqueProdutos();
	
	@Query(value= "SELECT E.produto_id, E.validade, sum(E.qtd) as qtd" +
			" FROM (select i.produto_id, i.validade, sum(i.qtd) as qtd" + 
					" from movimentacao m, item i" + 
					" where m.id = i.movimentacao_id" + 
					" group by i.produto_id, i.validade) E" +
			" WHERE age(E.validade, CURRENT_DATE) <= '30 days'\\:\\:interval" +
			" AND qtd > 0" +
			" group by E.produto_id, E.validade;", nativeQuery = true)
	List<Object[]> getEstoqueUrgente();

}
