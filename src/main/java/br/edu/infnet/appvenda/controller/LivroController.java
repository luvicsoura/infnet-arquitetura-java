package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.appvenda.model.service.LivroService;

public class LivroController {
	@Autowired
	private AppController appController;

	@Autowired
	private LivroService livroService;

	@GetMapping(value = "/livros/{id}/excluir")
	public String excluir(@PathVariable Integer id) {

		livroService.excluir(id);

		return "redirect:/livros/lista";
	}
}
