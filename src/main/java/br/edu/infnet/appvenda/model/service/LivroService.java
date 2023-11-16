package br.edu.infnet.appvenda.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Livro;
import br.edu.infnet.appvenda.model.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository livroRepository;

	public void incluir(Livro eletronico) {
		livroRepository.save(eletronico);
	}

	public Collection<Livro> obterLista() {
		return (Collection<Livro>) livroRepository.findAll();
	}

	public void excluir(Integer id) {
		livroRepository.deleteById(id);
	}

	public long obterQtde() {
		return livroRepository.count();
	}
}
