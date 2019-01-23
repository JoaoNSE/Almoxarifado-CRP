package br.ce.qxd.crp.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ce.qxd.crp.model.Item;
import br.ce.qxd.crp.model.Movimentacao;
import br.ce.qxd.crp.model.Produto;
import br.ce.qxd.crp.model.Usuario;
import br.ce.qxd.crp.repository.ItemRepository;
import br.ce.qxd.crp.repository.MovimentacaoRepository;
import br.ce.qxd.crp.repository.ProdutoRepository;

@Controller
public class MovimentacaoController {
	
	private String folder = "movimentacao/";
	private String itFolder = "item/";
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@GetMapping("/entradas")
	public String listarEntradas(Model model) {
		Iterable<Movimentacao> movs = movimentacaoRepo.findByTipo('E', new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
		
		model.addAttribute("movs", movs);
		model.addAttribute("tipo", 'E');
		return folder + "lista-entradas";
	}
	
	@PostMapping("/entradas")
	public String addEntrada(@Valid Movimentacao mov, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes, ModelMap modelMap) {
		if(result.hasErrors()) {
			model.addAttribute("mov", mov);
			model.addAttribute("acao", "/entradas");
			modelMap.put(BindingResult.class.getName() + ".mov", result);
			if (mov.getId() == null) {
				return folder + "adicionar-entrada";
			} else {
				return folder + "editar-entrada";
			}
		}
		
		if (mov.getId() == null) {
			mov = movimentacaoRepo.save(mov);
			
			return "redirect:/entradas/"+mov.getId()+"/update";
//			redirectAttributes.addFlashAttribute("msg", "Movimentação inserida com sucesso.");
		} else {
			movimentacaoRepo.save(mov);
			redirectAttributes.addFlashAttribute("msg", "Movimentação atualizada com sucesso.");
		}
		return "redirect:/entradas";
	}
	
	@GetMapping("/entradas/{id}")
	public String showEntradaDetalhes(Model model, @PathVariable Integer id) {
		Movimentacao mov = movimentacaoRepo.findOne(id);
		model.addAttribute("mov", mov);
//		model.addAttribute("itens", mov.getItens());
		
		return folder + "display-entrada";
	}
	
	@RequestMapping("/entradas/{id}/delete")
	public String deletaEntrada(Model model, @PathVariable Integer id,  RedirectAttributes redirectAttributes) {
		Movimentacao mov = new Movimentacao();
		mov.setId(id);
		
		movimentacaoRepo.delete(mov);
		redirectAttributes.addFlashAttribute("msg", "Entrada removida com sucesso.");
		return "redirect:/entradas";
	}
	
	@GetMapping("/entradas/add")
	public String showAddEntradaForm(Model model) {
		Movimentacao mov = new Movimentacao();
		mov.setTipo('E');
		model.addAttribute("mov", mov);
		model.addAttribute("acao", "/entradas");
		return folder + "adicionar-entrada";
	}
	
	
	@GetMapping("/entradas/{id}/update")
	public String showUpdateEntradaForm(Model model, @PathVariable Integer id) {
		Movimentacao mov = movimentacaoRepo.getOne(id);
		
		model.addAttribute("mov", mov);
		model.addAttribute("acao", "/entradas");
		model.addAttribute("movId", id);
		
		return folder + "editar-entrada";
	}
	
	@PostMapping("/entradas/{id}/update")
	public String addItemInEntrada(@Valid Item item, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes, @PathVariable Integer id) {
		if(result.hasErrors()) {
			System.out.println("tem erro");
			model.addAttribute("item", item);
			model.addAttribute("acao", "/entradas/"+id+"/update");
			if (item.getId() == null) {
				return itFolder + "adicionar-item";
			} else {
				return itFolder + "editar-item";
			}
		}
		
		if (item.getId() == null) {
			itemRepo.save(item);
			
			
//			redirectAttributes.addFlashAttribute("msg", "Movimentação inserida com sucesso.");
		} else {
			itemRepo.save(item);
//			redirectAttributes.addFlashAttribute("msg", "Movimentação atualizada com sucesso.");
		}
		return "redirect:"+"/entradas/"+id+"/update";
	}
	
	@GetMapping("/entradas/{id}/itens/add")
	public String showAddItemEntradaForm(Model model, @PathVariable Integer id) {
		Item it = new Item();
		Movimentacao mov = movimentacaoRepo.findOne(id);
		it.setMovimentacao(mov);
		
		Iterable<Produto> produtos = produtoRepo.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "nome")));

		model.addAttribute("produtos", produtos);
		model.addAttribute("item", it);		
		model.addAttribute("acao", "/entradas/"+id+"/update");
		return itFolder + "adicionar-item";
	}
	
	@GetMapping("entradas/{id}/itens/{itId}/update")
	public String showUpdateItEntradaForm(Model model, @PathVariable Integer id, @PathVariable Integer itId) {
		Item it = itemRepo.findOne(itId);
		Iterable<Produto> produtos = produtoRepo.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "nome")));

		model.addAttribute("produtos", produtos);
		model.addAttribute("item", it);
		model.addAttribute("acao", "/entradas/"+id+"/update");
		return itFolder + "editar-item";
	}
	
	@RequestMapping("entradas/{id}/itens/{itId}/delete")
	public String deleteItemFromEntrada(Model model, @PathVariable Integer id, @PathVariable Integer itId, RedirectAttributes redirectAttributes) {
		Item it = itemRepo.findOne(itId);
		
		
		itemRepo.delete(it);
		redirectAttributes.addFlashAttribute("msg", "Item removido com sucesso.");
		return "redirect:/entradas/"+id+"/update";
	}
	
	//SAíDAAAS =======================================================================================================
	//================================================================================================================
	//================================================================================================================
	//================================================================================================================
	
	
	@GetMapping("/saidas")
	public String listarSaidas(Model model) {
		Iterable<Movimentacao> movs = movimentacaoRepo.findByTipo('S', new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
		
		model.addAttribute("movs", movs);
		model.addAttribute("tipo", 'S');
		return folder + "lista-saidas";
	}
	
	@PostMapping("/saidas")
	public String addSaida(@Valid Movimentacao mov, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes, ModelMap modelMap) {
		if(result.hasErrors()) {
			model.addAttribute("mov", mov);
			model.addAttribute("acao", "/saidas");
			modelMap.put(BindingResult.class.getName() + ".mov", result);
			if (mov.getId() == null) {
				return folder + "adicionar-saida";
			} else {
				return folder + "editar-saida";
			}
		}
		
		for (Item i : mov.getItens()) {
			i.setQtd(i.getQtd()*-1);
		}
		
		if (mov.getId() == null) {
			mov = movimentacaoRepo.save(mov);
			
			return "redirect:/saidas/"+mov.getId()+"/update";
//			redirectAttributes.addFlashAttribute("msg", "Movimentação inserida com sucesso.");
		} else {
			movimentacaoRepo.save(mov);
			redirectAttributes.addFlashAttribute("msg", "Movimentação atualizada com sucesso.");
		}
		return "redirect:/saidas";
	}
	
	@GetMapping("/saidas/add")
	public String showAddSaidaForm(Model model) {
		Movimentacao mov = new Movimentacao();
		mov.setTipo('S');
		model.addAttribute("mov", mov);
		model.addAttribute("acao", "/saidas");
		return folder + "adicionar-saida";
	}
	
	@GetMapping("/saidas/{id}")
	public String showSaidaDetalhes(Model model, @PathVariable Integer id) {
		Movimentacao mov = movimentacaoRepo.findOne(id);
		for (Item i : mov.getItens()) {
			i.setQtd(i.getQtd()*-1);
		}
		model.addAttribute("mov", mov);
//		model.addAttribute("itens", mov.getItens());
		
		return folder + "display-saida";
	}
	
	@RequestMapping("/saidas/{id}/delete")
	public String deletaSaida(Model model, @PathVariable Integer id,  RedirectAttributes redirectAttributes) {
		Movimentacao mov = new Movimentacao();
		mov.setId(id);
		
		movimentacaoRepo.delete(mov);
		redirectAttributes.addFlashAttribute("msg", "Saída removida com sucesso.");
		return "redirect:/saidas";
	}
	
	@GetMapping("/saidas/{id}/update")
	public String showUpdateSaidaForm(Model model, @PathVariable Integer id) {
		Movimentacao mov = movimentacaoRepo.getOne(id);
		
		for (Item i : mov.getItens()) {
			i.setQtd(i.getQtd()*-1);
		}
		
		model.addAttribute("mov", mov);
		model.addAttribute("acao", "/saidas");
		model.addAttribute("movId", id);
		
		return folder + "editar-saida";
	}
	
	@PostMapping("/saidas/{id}/update")
	public String addItemInSaida(@Valid Item item, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes, @PathVariable Integer id) {
		if(result.hasErrors()) {
			model.addAttribute("item", item);
			model.addAttribute("acao", "/saidas/"+id+"/update");
			if (item.getId() == null) {
				return itFolder + "adicionar-item";
			} else {
				return itFolder + "editar-item";
			}
		}
		item.setQtd(item.getQtd()*-1);
		if (item.getId() == null) {
			itemRepo.save(item);
			
			
//			redirectAttributes.addFlashAttribute("msg", "Movimentação inserida com sucesso.");
		} else {
			itemRepo.save(item);
//			redirectAttributes.addFlashAttribute("msg", "Movimentação atualizada com sucesso.");
		}
		return "redirect:"+"/saidas/"+id+"/update";
	}
	
	@GetMapping("/saidas/{id}/itens/add")
	public String showAddItemSaidaForm(Model model, @PathVariable Integer id) {
		Item it = new Item();
		Movimentacao mov = movimentacaoRepo.findOne(id);
		it.setMovimentacao(mov);
		
		Iterable<Produto> produtos = produtoRepo.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "nome")));

		model.addAttribute("produtos", produtos);
		model.addAttribute("item", it);		
		model.addAttribute("acao", "/saidas/"+id+"/update");
		return itFolder + "adicionar-item";
	}
	
	@GetMapping("saidas/{id}/itens/{itId}/update")
	public String showUpdateItSaidaForm(Model model, @PathVariable Integer id, @PathVariable Integer itId) {
		Item it = itemRepo.findOne(itId);
		it.setQtd(it.getQtd()*-1);
		Iterable<Produto> produtos = produtoRepo.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "nome")));

		model.addAttribute("produtos", produtos);
		model.addAttribute("item", it);
		model.addAttribute("acao", "/saidas/"+id+"/update");
		return itFolder + "editar-item";
	}
	
	@RequestMapping("saidas/{id}/itens/{itId}/delete")
	public String deleteItemFromSaida(Model model, @PathVariable Integer id, @PathVariable Integer itId, RedirectAttributes redirectAttributes) {
		Item it = itemRepo.findOne(itId);
		
		
		itemRepo.delete(it);
		redirectAttributes.addFlashAttribute("msg", "Item removido com sucesso.");
		return "redirect:/saidas/"+id+"/update";
	}
	
	@RequestMapping("nullptr")
	public String nullPtr() {
		throw new NullPointerException();
	}
}
