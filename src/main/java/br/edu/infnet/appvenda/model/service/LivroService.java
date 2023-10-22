package br.edu.infnet.appvenda.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Livro;

@Service
public class LivroService {
	private Map<Integer, Livro> mapaLivro = new HashMap<Integer, Livro>();

	public void incluir(Livro livro) {
		mapaLivro.put(livro.getCodigo(), livro);
	}

	public Collection<Livro> obterLista() {
		return mapaLivro.values();
	}
}
