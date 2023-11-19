package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import br.edu.infnet.appvenda.model.service.LivroService;
import br.edu.infnet.appvenda.model.service.ProdutoService;
import br.edu.infnet.appvenda.model.service.VeiculoService;
import br.edu.infnet.appvenda.model.service.VendedorService;

@Controller
public class AppController {

	private final VendedorService vendedorService;
	private final ProdutoService produtoService;
	private final LivroService livroService;
	private final VeiculoService veiculoService;

	@Autowired
	public AppController(
			ProdutoService produtoService,
			LivroService livroService,
			VeiculoService veiculoService,
			VendedorService vendedorService) {
		this.produtoService = produtoService;
		this.livroService = livroService;
		this.veiculoService = veiculoService;
		this.vendedorService = vendedorService;
	}

	@GetMapping(value = "/")
	public String showHome(Model model) {

		model.addAttribute("qtdeVendedor", vendedorService.obterQtde());
		model.addAttribute("qtdeProduto", produtoService.obterQtde());
		model.addAttribute("qtdeLivros", livroService.obterQtde());
		model.addAttribute("qtdeVeiculos", veiculoService.obterQtde());

		return "home";
	}

	@GetMapping(value = "/produto/lista")
	public String obterListaProduto(Model model) {
		model.addAttribute("rota", "produto");
		model.addAttribute("titulo", "Produtos:");
		model.addAttribute("listagem", produtoService.obterLista());

		return showHome(model);
	}
}