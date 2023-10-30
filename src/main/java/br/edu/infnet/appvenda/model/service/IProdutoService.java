package br.edu.infnet.appvenda.model.service;

import java.util.Collection;

import br.edu.infnet.appvenda.model.domain.Produto;

public interface IProdutoService {
	void incluir(Produto produto);

	Collection<Produto> obterLista();
}
