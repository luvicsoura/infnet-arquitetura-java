package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.appvenda.model.service.LivroService;

@Controller
public class LivroController {
	private final LivroService livroService;

	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@GetMapping(value = "/livros/{id}/excluir")
	public String excluir(@PathVariable Integer id) {

		livroService.excluir(id);

		return "redirect:/livros/lista";
	}
}
