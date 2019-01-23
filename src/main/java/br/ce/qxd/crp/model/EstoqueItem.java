package br.ce.qxd.crp.model;

import java.time.LocalDate;

public class EstoqueItem {

	private Produto produto;
	private LocalDate validade;
	private Integer qtd;
	
	public EstoqueItem () {
		
		
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public LocalDate getValidade() {
		return validade;
	}
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	
}
