package br.ce.qxd.crp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ce.qxd.crp.model.Usuario;

import br.ce.qxd.crp.repository.UsuarioRepository;

@Controller
public class LoginController {
	
	private String folder = "login/";
	
	@Autowired
	private UsuarioRepository usuarioRepo;

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
		
		List<Usuario> usuarios = usuarioRepo.findAll();
		model.addAttribute("usuarios", usuarios);
		
		return folder + "lista-usuarios";
	}
	
	/*@PostMapping("usuarios")
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
		List<Usuario> usarios = usuarioRepo.findByNome(usuario.getNome());
		if (usarios.size() == 1 ) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("acao", "/usuarios");
			if (usuario.getId() == null) {
				
				model.addAttribute("msg", "Cadastro falhou: Usuário já existe");

				return folder + "/cadastrar-usuario";
			} else if (usuario.getId() != usarios.get(0).getId()){
				model.addAttribute("msg", "Edição falhou: Usuário já existe");

				return folder + "/editar-usuario";
			}
		}
		
		MessageDigest algorithm;
		byte messageDigest[] = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			messageDigest = algorithm.digest(usuario.getSenha().getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		System.out.println("Senha: " + usuario.getSenha());
		String senha = hexString.toString();
		System.out.println("Senha: " + senha);
		
		usuario.setSenha(senha);
		
		if (usuario.getId() == null) {
			usuarioRepo.save(usuario);
			redirectAttributes.addFlashAttribute("msg", "Usuario cadastrado com sucesso.");
		} else {
			usuarioRepo.save(usuario);
			redirectAttributes.addFlashAttribute("msg", "Usuario atualizado com sucesso.");
		}
		
		return "redirect:/usuarios";
	}*/
	
	@GetMapping("usuarios/add")
	public String showUsuarioForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("acao", "/usuarios");
		
		return folder + "cadastrar-usuario";
	}
	
	@RequestMapping("usuarios/{id}/update")
	public String updateUsuarioForm(Model model, @PathVariable Integer id) {
		Usuario p = usuarioRepo.findOne(id);
		
		p.setSenha("");
		
		model.addAttribute("usuario", p);
		model.addAttribute("acao", "/usuarios");
		return folder + "editar-usuario";
	}
	
	@RequestMapping("/usuarios/{id}/delete")
	public String deletarUsuario(Model model, @PathVariable Integer id,  RedirectAttributes redirectAttributes) {
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		usuarioRepo.delete(usuario);
		redirectAttributes.addFlashAttribute("msg", "Usuário removido com sucesso.");
		
		return "redirect:/usuarios";
	
	}
	
	
}
