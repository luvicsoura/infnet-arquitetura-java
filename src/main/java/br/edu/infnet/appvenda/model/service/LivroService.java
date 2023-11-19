package br.edu.infnet.appvenda.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Livro;
import br.edu.infnet.appvenda.model.domain.Veiculo;
import br.edu.infnet.appvenda.model.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository livroRepository;

	public enum OrdenarPor {
		AUTOR("autor"),
		ANO_PUBLICACAO("anoPublicacao"),
		TITULO("titulo"),
		PRECO("preco"),
		EDITORA("editora");

		private final String text;

		OrdenarPor(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	public void incluir(Livro eletronico) {
		livroRepository.save(eletronico);
	}

	public Collection<Livro> obterLista() {
		return obterLista(OrdenarPor.TITULO);
	}

	public Collection<Livro> obterLista(OrdenarPor ordenarPor) {
		return obterLista(ordenarPor, Sort.Direction.ASC);
	}

	public Collection<Livro> obterLista(OrdenarPor ordenarPor, Sort.Direction ordem) {

		Comparator<Livro> comparador;
		if (ordenarPor == OrdenarPor.PRECO || ordenarPor == OrdenarPor.ANO_PUBLICACAO) {
			comparador = Comparator.comparing(
					(Livro veiculo) -> {
						switch (ordenarPor) {
							case PRECO:
								return Double.valueOf(veiculo.getPreco());

							case ANO_PUBLICACAO:
							default:
								return Double.valueOf(veiculo.getAnoPublicacao());
						}
					});
		} else {
			comparador = Comparator.comparing(
					(Livro livro) -> {
						switch (ordenarPor) {
							case AUTOR:
								return livro.getAutor();

							case EDITORA:
								return livro.getEditora();

							case TITULO:
							default:
								return livro.getTitulo();
						}
					});
		}

		Iterable<Livro> livros = livroRepository.findAll();
		return StreamSupport.stream(livros.spliterator(), false)
				.sorted(ordem == Sort.Direction.ASC ? comparador : comparador.reversed())
				.collect(java.util.stream.Collectors.toCollection(ArrayList<Livro>::new));
	}

	public Livro obterPorId(Integer id) {
		return livroRepository.findById(id).orElse(null);
	}

	public void atualizarPorId(Integer id, Livro livro) {
		livro.setId(id);
		livroRepository.save(livro);
	}

	public void excluir(Integer id) {
		livroRepository.deleteById(id);
	}

	public long obterQtde() {
		return livroRepository.count();
	}
}
