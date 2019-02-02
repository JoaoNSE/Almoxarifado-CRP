package br.ce.qxd.crp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ce.qxd.crp.exception.UserAlreadyExistsException;
import br.ce.qxd.crp.model.Usuario;
import br.ce.qxd.crp.service.UsuarioService;

@Controller
public class LoginController {
	
	private String folder = "login/";
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("login")
	public String mostraFormLogin(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String logout) {
		if (error != null) {
			model.addAttribute("msg", "Usuário ou senha inválidos!");
		}
		if (logout != null) {
			model.addAttribute("logoutMsg", "Logout efetuado com sucesso!");
		}
		model.addAttribute("acao", "/signin");
		return folder + "login";
	}
	
	@GetMapping("usuarios")
	public String listaUsuarios(Model model) {
		
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		
		return folder + "lista-usuarios";
	}
	
	@PostMapping("usuarios")
	public String cadastrarUsuario(@Valid Usuario usuario, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("acao", "/usuarios");
			if (usuario.getId() == null) {
				return folder + "/cadastrar-usuario";
			} else {
				return folder + "/editar-usuario";
			}
		}
		
		try {
			usuarioService.addUpdateUsuario(usuario);
			
		} catch (UserAlreadyExistsException e) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("acao", "/usuarios");
			model.addAttribute("msg", "Cadastro falhou: " + e.getMessage());
			return folder + "/cadastrar-usuario";
			
		} catch (BadCredentialsException e) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("acao", "/usuarios");
			model.addAttribute("msg", "Edição falhou: " + e.getMessage());
			return folder + "/editar-usuario";
		} 
		
		if (usuario.getId() == null) {
			redirectAttributes.addFlashAttribute("msg", "Usuario cadastrado com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Usuario atualizado com sucesso.");
		}
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("usuarios/add")
	public String showUsuarioForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("acao", "/usuarios");
		
		return folder + "cadastrar-usuario";
	}
	
	@RequestMapping("usuarios/{id}/update")
	public String updateUsuarioForm(Model model, @PathVariable Integer id) {
		Usuario p = usuarioService.findOne(id);

		p.setSenha("");
		
		model.addAttribute("usuario", p);
		model.addAttribute("acao", "/usuarios");
		return folder + "editar-usuario";
	}
	
	@RequestMapping("/usuarios/{id}/delete")
	public String deletarUsuario(Model model, @PathVariable Integer id,  RedirectAttributes redirectAttributes) {
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		usuarioService.delete(usuario);
		redirectAttributes.addFlashAttribute("msg", "Usuário removido com sucesso.");
		
		return "redirect:/usuarios";
	
	}
	
	
}
