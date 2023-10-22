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
import br.edu.infnet.appvenda.model.domain.Veiculo;
import br.edu.infnet.appvenda.model.service.ProdutoService;

@Order(0)
@Component
public class ProdutoLoader implements ApplicationRunner {

	@Autowired
	private ProdutoService produtoService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("files/produtos.txt");
		BufferedReader leitura = new BufferedReader(file);

		String linha = leitura.readLine();

		String[] campos = null;

		while (linha != null) {

			campos = linha.split(";");

			switch (campos[7]) {
				case "L":
					// id;titulo;preço;em estoque?; autor; editora; ano da publicação; tipo de
					// produto
					Livro livro = new Livro();
					livro.setCodigo(Integer.valueOf(campos[0]));
					livro.setTitulo(campos[1]);
					livro.setPreco(Float.valueOf(campos[2]));
					livro.setEstoque(Boolean.valueOf(campos[3]));
					livro.setAutor(campos[4]);
					livro.setEditora(campos[5]);
					livro.setAnoPublicacao(Integer.valueOf(campos[6]));

					produtoService.incluir(livro);

					break;

				case "V":
					// id, descrição do veículo, preço, estoque, marca, modelo e ano do veículo;
					// tipo de produto

					Veiculo veiculo = new Veiculo();
					veiculo.setCodigo(Integer.valueOf(campos[0]));
					veiculo.setDescricao(campos[1]);
					veiculo.setPreco(Float.valueOf(campos[2]));
					veiculo.setEstoque(Boolean.valueOf(campos[3]));
					veiculo.setMarca(campos[4]);
					veiculo.setModelo(campos[5]);
					veiculo.setAno(Integer.valueOf(campos[6]));

					produtoService.incluir(veiculo);

					break;

				default:
					break;
			}

			linha = leitura.readLine();
		}

		for (Produto produto : produtoService.obterLista()) {
			System.out.println("[Produto] " + produto);
		}

		leitura.close();
	}
}