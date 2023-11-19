package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.appvenda.model.domain.Livro;
import br.edu.infnet.appvenda.model.service.LivroService;

@Controller
public class LivroController {
	private final LivroService livroService;
	private final AppController appController;

	@Autowired
	public LivroController(LivroService livroService, AppController appController) {
		this.livroService = livroService;
		this.appController = appController;
	}

	@GetMapping(value = "/livros/lista")
	public String obterListaLivros(
			Model model,
			@RequestParam(name = "ordenarPor", required = false) String ordenarPor,
			@RequestParam(name = "ordem", required = false) String ordem) {
		model.addAttribute("rota", "livros");
		model.addAttribute("titulo", "Livros:");

		LivroService.OrdenarPor ordenarPorEnum = null;
		Sort.Direction ordemEnum = null;

		if (ordem != null) {
			ordemEnum = Sort.Direction.valueOf(ordem.toUpperCase());
		}

		if (ordenarPor != null) {
			try {
				ordenarPorEnum = LivroService.OrdenarPor.valueOf(ordenarPor.toUpperCase());
			} catch (IllegalArgumentException e) {
			}
		}

		if (ordenarPorEnum != null && ordemEnum != null) {
			model.addAttribute("listagem", livroService.obterLista(ordenarPorEnum, ordemEnum));
		} else if (ordenarPor != null) {
			model.addAttribute("listagem", livroService.obterLista(ordenarPorEnum));
		} else {
			model.addAttribute("listagem", livroService.obterLista());
		}

		return appController.showHome(model);
	}

	@GetMapping(value = "/livros/novo")
	public String showNovoLivro(Model model) {
		model.addAttribute("rota", "livros_novo");
		model.addAttribute("titulo", "Novo Livro");

		return appController.showHome(model);
	}

	@GetMapping(value = "/livros/{id}")
	public String ver(
			Model model,
			@PathVariable Integer id) {
		model.addAttribute("rota", "livros_unico");
		model.addAttribute("id", id);
		model.addAttribute("editar", false);
		model.addAttribute("livro", livroService.obterPorId(id));

		return appController.showHome(model);
	}

	@GetMapping(value = "/livros/{id}/editar")
	public String editar(
			Model model,
			@PathVariable Integer id) {
		model.addAttribute("rota", "livros_unico");
		model.addAttribute("id", id);
		model.addAttribute("editar", true);
		model.addAttribute("livro", livroService.obterPorId(id));

		return appController.showHome(model);
	}

	@PostMapping(value = "/livros")
	public String incluir(@ModelAttribute Livro livro) {
		livroService.incluir(livro);
		return "redirect:/livros/lista";
	}

	@PostMapping(value = "/livros/{id}")
	public String atualizar(
			@PathVariable Integer id,
			@ModelAttribute Livro livro) {
		livroService.atualizarPorId(id, livro);

		return "redirect:/livros/lista";
	}

	@GetMapping(value = "/livros/{id}/excluir")
	public String excluir(@PathVariable Integer id) {

		livroService.excluir(id);

		return "redirect:/livros/lista";
	}
}
