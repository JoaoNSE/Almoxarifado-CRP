package br.ce.qxd.crp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ce.qxd.crp.model.Produto;
import br.ce.qxd.crp.repository.ProdutoRepository;



@Controller
public class ProdutoController {
	
	private String folder = "produto/";
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		return "redirect:/produtos";
	}
	
	@GetMapping("/produtos")
	public String listarProdutos(Model model) {
//		List<Produto> produtos = new ProdutoJdbcDao().getLista();
		Iterable<Produto> produtos = produtoRepo.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
		
		model.addAttribute("produtos", produtos);
		
		return folder + "lista-produtos";
	}
	
	@PostMapping("/produtos")
	public String addProduto(@Valid Produto produto, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		
//		req.setCharacterEncoding("UTF-8");
		
//		produto.setNome(req.getParameter("nome"));
		
		if(result.hasErrors()) {
			model.addAttribute("produto", produto);
			model.addAttribute("acao", "/produtos");
			if (produto.getId() == null) {
				return folder + "adicionar-produto";
			} else {
				return folder + "editar-produto";
			}
		}
		
		if (produto.getId() == null) {
			produtoRepo.save(produto);
			redirectAttributes.addFlashAttribute("msg", "Produto inserido com sucesso.");
		} else {
			produtoRepo.save(produto);
			redirectAttributes.addFlashAttribute("msg", "Produto atualizado com sucesso.");
		}
		return "redirect:/produtos";
	}
	
	@RequestMapping("/produtos/{id}/delete")
	public String deletarProduto(Model model, @PathVariable Integer id,  RedirectAttributes redirectAttributes) {
		
		Produto produto = new Produto();
		produto.setId(id);
		
		produtoRepo.delete(produto);
		redirectAttributes.addFlashAttribute("msg", "Produto removido com sucesso.");
		
		return "redirect:/produtos";
	
	}
	
	@GetMapping("/produtos/add")
	public String showAddProdutoForm(Model model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("acao", "/produtos");
		return folder + "adicionar-produto";
	}
	
	
	@RequestMapping("/produtos/{id}/update")
	public String showUpdateProdutoForm(Model model, @PathVariable Integer id) {
		Produto p = produtoRepo.findOne(id);
		
		model.addAttribute("produto", p);
		model.addAttribute("acao", "/produtos");
		return folder + "editar-produto";
	}
	
	

	
	
}
