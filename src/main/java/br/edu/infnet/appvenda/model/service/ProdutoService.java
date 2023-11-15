package br.edu.infnet.appvenda.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Produto;
import br.edu.infnet.appvenda.model.domain.Vendedor;
import br.edu.infnet.appvenda.model.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public void incluir(Produto produto) {
		produtoRepository.save(produto);
	}

	public Collection<Produto> obterLista() {
		return (Collection<Produto>) produtoRepository.findAll();
	}

	public Collection<Produto> obterLista(Vendedor vendedor) {
		return (Collection<Produto>) produtoRepository.obterLista(vendedor.getId());
	}

	public Collection<Produto> obterLista(Integer id) {
		return (Collection<Produto>) produtoRepository.obterLista(id);
	}

	public long obterQtde() {
		return produtoRepository.count();
	}
}