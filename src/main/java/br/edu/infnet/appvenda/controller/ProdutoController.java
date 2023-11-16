package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.appvenda.model.service.ProdutoService;

@Controller
public class ProdutoController {

	@Autowired
	private AppController appController;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/produto/{id}/excluir")
	public String excluir(@PathVariable Integer id) {

		produtoService.excluir(id);

		return "redirect:/produto/lista";
	}
}