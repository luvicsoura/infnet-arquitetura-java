package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.appvenda.model.service.LivroService;
import br.edu.infnet.appvenda.model.service.VeiculoService;

public class VeiculoController {
	@Autowired
	private AppController appController;

	@Autowired
	private VeiculoService veiculoService;

	@GetMapping(value = "/veiculos/{id}/excluir")
	public String excluir(@PathVariable Integer id) {

		veiculoService.excluir(id);

		return "redirect:/veiculos/lista";
	}
}
