package br.edu.infnet.appvenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.infnet.appvenda.model.service.LivroService;
import br.edu.infnet.appvenda.model.service.ProdutoService;
import br.edu.infnet.appvenda.model.service.VeiculoService;
import br.edu.infnet.appvenda.model.service.VendedorService;

@Controller
public class AppController {

	@Autowired
	private VendedorService vendedorService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private LivroService livroService;
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping(value = "/")
	public String showHome(Model model) {

		model.addAttribute("qtdeVendedor", vendedorService.obterQtde());
		model.addAttribute("qtdeProduto", produtoService.obterQtde());
		model.addAttribute("qtdeLivros", livroService.obterQtde());
		model.addAttribute("qtdeEletronico", veiculoService.obterQtde());

		return "home";
	}

	@GetMapping(value = "/produto/lista")
	public String obterListaProduto(Model model) {

		model.addAttribute("titulo", "Produtos:");
		model.addAttribute("listagem", produtoService.obterLista());

		return showHome(model);
	}

	@GetMapping(value = "/livro/lista")
	public String obterListaAlimenticio(Model model) {

		model.addAttribute("titulo", "Livros:");
		model.addAttribute("listagem", livroService.obterLista());

		return showHome(model);
	}

	@GetMapping(value = "/veiculo/lista")
	public String obterListaEletronico(Model model) {

		model.addAttribute("titulo", "Veículos:");
		model.addAttribute("listagem", veiculoService.obterLista());

		return showHome(model);
	}
}