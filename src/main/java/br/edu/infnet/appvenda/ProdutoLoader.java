package br.edu.infnet.appvenda;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.appvenda.model.domain.Livro;
import br.edu.infnet.appvenda.model.domain.Produto;
import br.edu.infnet.appvenda.model.service.ProdutoService;

@Order(0)
@Component
public class ProdutoLoader implements ApplicationRunner {

	@Autowired
	private ProdutoService produtoService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("files/livros.txt");
		BufferedReader leitura = new BufferedReader(file);

		String linha = leitura.readLine();

		String[] campos = null;

		while (linha != null) {

			campos = linha.split(";");

			// id;titulo;preço;em estoque?; autor; editora; ano da publicação
			Livro livro = new Livro();
			livro.setCodigo(Integer.valueOf(campos[AttributoLivro.ID.getValue()]));
			livro.setTitulo(campos[AttributoLivro.TITULO.getValue()]);
			livro.setPreco(Float.valueOf(campos[AttributoLivro.PRECO.getValue()]));
			livro.setEstoque(Boolean.valueOf(campos[AttributoLivro.EM_ESTOQUE.getValue()]));
			livro.setAutor(campos[AttributoLivro.AUTOR.getValue()]);
			livro.setEditora(campos[AttributoLivro.EDITORA.getValue()]);
			livro.setAnoPublicacao(Integer.valueOf(campos[AttributoLivro.ANO_PUBLICACAO.getValue()]));

			produtoService.incluir(livro);

			linha = leitura.readLine();
		}

		for (Produto produto : produtoService.obterLista()) {
			System.out.println("[Produto] " + produto);
		}

		leitura.close();
	}

	private enum AttributoLivro {
		ID(0),
		TITULO(1),
		PRECO(2),
		EM_ESTOQUE(3),
		AUTOR(4),
		EDITORA(5),
		ANO_PUBLICACAO(6);

		private final int value;

		private AttributoLivro(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}