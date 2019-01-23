package br.ce.qxd.crp.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=1, max=100, message="O tamanho deve ser entre {min} e {max}")
	private String obs;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")	
	@NotNull(message="Uma data deve ser inserida.")
	private LocalDate data;
	
	@NotNull
	private char tipo;
	
	@OneToMany(mappedBy="movimentacao", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Item> itens;
	
	public Movimentacao() {
		itens = new ArrayList<Item>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	
	
	
}
