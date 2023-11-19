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

import br.edu.infnet.appvenda.model.domain.Veiculo;
import br.edu.infnet.appvenda.model.service.VeiculoService;

@Controller
public class VeiculoController {
	private final VeiculoService veiculoService;
	private final AppController appController;

	@Autowired
	public VeiculoController(VeiculoService veiculoService, AppController appController) {
		this.veiculoService = veiculoService;
		this.appController = appController;
	}

	@GetMapping(value = "/veiculos/lista")
	public String obterListaVeiculo(
			Model model,
			@RequestParam(name = "ordenarPor", required = false) String ordenarPor,
			@RequestParam(name = "ordem", required = false) String ordem) {
		model.addAttribute("rota", "veiculos");
		model.addAttribute("titulo", "Veículos:");

		VeiculoService.OrdenarPor ordenarPorEnum = null;
		Sort.Direction ordemEnum = null;

		if (ordem != null) {
			ordemEnum = Sort.Direction.valueOf(ordem.toUpperCase());
		}

		if (ordenarPor != null) {
			try {
				ordenarPorEnum = VeiculoService.OrdenarPor.valueOf(ordenarPor.toUpperCase());
			} catch (IllegalArgumentException e) {
			}
		}

		if (ordenarPorEnum != null && ordemEnum != null) {
			model.addAttribute("listagem", veiculoService.obterLista(ordenarPorEnum, ordemEnum));
		} else if (ordenarPor != null) {
			model.addAttribute("listagem", veiculoService.obterLista(ordenarPorEnum));
		} else {
			model.addAttribute("listagem", veiculoService.obterLista());
		}

		return appController.showHome(model);
	}

	@GetMapping(value = "/veiculos/novo")
	public String showNovoVeiculo(Model model) {
		model.addAttribute("rota", "veiculos_novo");
		model.addAttribute("titulo", "Novo Veículo");

		return appController.showHome(model);
	}

	@GetMapping(value = "/veiculos/{id}")
	public String ver(
			Model model,
			@PathVariable Integer id) {
		model.addAttribute("rota", "veiculos_unico");
		model.addAttribute("id", id);
		model.addAttribute("editar", false);
		model.addAttribute("veiculo", veiculoService.obterPorId(id));

		return appController.showHome(model);
	}

	@GetMapping(value = "/veiculos/{id}/editar")
	public String editar(
			Model model,
			@PathVariable Integer id) {
		model.addAttribute("rota", "veiculos_unico");
		model.addAttribute("id", id);
		model.addAttribute("editar", true);
		model.addAttribute("veiculo", veiculoService.obterPorId(id));

		return appController.showHome(model);
	}

	@PostMapping(value = "/veiculos")
	public String incluir(@ModelAttribute Veiculo veiculo) {
		veiculoService.incluir(veiculo);
		return "redirect:/veiculos/lista";
	}

	@PostMapping(value = "/veiculos/{id}")
	public String atualizar(
			@PathVariable Integer id,
			@ModelAttribute Veiculo veiculo) {
		veiculoService.atualizarPorId(id, veiculo);

		return "redirect:/livros/lista";
	}

	@GetMapping(value = "/veiculos/{id}/excluir")
	public String excluir(@PathVariable Integer id) {

		veiculoService.excluir(id);

		return "redirect:/veiculos/lista";
	}
}
