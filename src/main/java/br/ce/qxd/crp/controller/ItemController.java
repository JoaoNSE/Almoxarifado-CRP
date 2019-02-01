package br.ce.qxd.crp.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ce.qxd.crp.model.EstoqueItem;
import br.ce.qxd.crp.repository.ItemRepository;
import br.ce.qxd.crp.repository.ProdutoRepository;

@Controller
public class ItemController {

	private String folder = "item/";
	
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@RequestMapping("/estoque/validades")
	public String verEstoqueValidades(Model model) {
		List<Object[]> objects = itemRepo.getEstoque();
		
		ArrayList<Object[]> temp = new ArrayList<Object[]>(objects);
		
		ArrayList<EstoqueItem> itens = new ArrayList<EstoqueItem>();
		for (int i = 0; i < temp.size(); i++) {
			EstoqueItem tempi = new EstoqueItem();
			tempi.setProduto(produtoRepo.findOne((Integer)temp.get(i)[0]));
			
			tempi.setValidade(Instant.ofEpochMilli(((Date) temp.get(i)[1]).getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
			tempi.setQtd(Integer.valueOf(((BigInteger)temp.get(i)[2]).intValue()));
			itens.add(tempi);
		}
		model.addAttribute("itens", itens);
		
		return folder + "lista-estoque";
	}
	
	@RequestMapping("/estoque/produtos")
	public String verEstoqueProdutos(Model model) {
		List<Object[]> objects = itemRepo.getEstoqueProdutos();
		
		ArrayList<Object[]> temp = new ArrayList<Object[]>(objects);
		
		ArrayList<EstoqueItem> itens = new ArrayList<EstoqueItem>();
		for (int i = 0; i < temp.size(); i++) {
			EstoqueItem tempi = new EstoqueItem();
			tempi.setProduto(produtoRepo.findOne((Integer)temp.get(i)[0]));
			
			tempi.setQtd(Integer.valueOf(((BigInteger)temp.get(i)[1]).intValue()));
			itens.add(tempi);
		}
		model.addAttribute("itens", itens);
		
		return folder + "lista-estoque-produtos";
	}
	
	@RequestMapping("/urgente")
	public String verUrgentes(Model model) {
		List<Object[]> objects = itemRepo.getEstoqueUrgente();
		
		ArrayList<Object[]> temp = new ArrayList<Object[]>(objects);
		
		ArrayList<EstoqueItem> itens = new ArrayList<EstoqueItem>();
		for (int i = 0; i < temp.size(); i++) {
			EstoqueItem tempi = new EstoqueItem();
			tempi.setProduto(produtoRepo.findOne((Integer)temp.get(i)[0]));
			
			tempi.setValidade(Instant.ofEpochMilli(((Date) temp.get(i)[1]).getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
			tempi.setQtd(Integer.valueOf( ( (BigDecimal)temp.get(i)[2]).intValue() ));
			itens.add(tempi);
		}
		model.addAttribute("itens", itens);
		model.addAttribute("n_urgentes", objects.size());
		
		return folder + "lista-urgente";
	}
	
	@ResponseBody
	@PostMapping(value = "/getValSize")
	public String getSearchResultViaAjax() {
		List<Object[]> objects = itemRepo.getEstoqueUrgente();
		Integer size = objects.size();
		
		if(size == 0) {
			return "";
		} else {
			return ""+size;
		}

	}
}
