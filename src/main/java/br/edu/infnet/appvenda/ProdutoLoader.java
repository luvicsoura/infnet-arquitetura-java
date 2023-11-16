package br.edu.infnet.appvenda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.appvenda.model.domain.Livro;
import br.edu.infnet.appvenda.model.domain.Produto;
import br.edu.infnet.appvenda.model.domain.Veiculo;
import br.edu.infnet.appvenda.model.domain.Vendedor;
import br.edu.infnet.appvenda.model.service.ProdutoService;
import br.edu.infnet.appvenda.model.service.VendedorService;
import jakarta.validation.ConstraintViolationException;

@Order(2)
@Component
public class ProdutoLoader implements ApplicationRunner {

	private final String SEPARADOR = ",";

	private final ProdutoService produtoService;

	private final VendedorService vendedorService;

	@Autowired
	public ProdutoLoader(VendedorService vendedorService, ProdutoService produtoService) {
		this.vendedorService = vendedorService;
		this.produtoService = produtoService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Collection<Produto> produtosExistentes = produtoService.obterLista();

		if (!produtosExistentes.isEmpty()) {
			return;
		}

		FileReader file = new FileReader("files/produtos.txt");
		BufferedReader leitura = new BufferedReader(file);

		String linha = leitura.readLine();

		String[] campos = null;

		Vendedor vendedor = new Vendedor();

		while (linha != null) {

			campos = linha.split(SEPARADOR);

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

					vendedor.setId(Integer.valueOf(campos[8]));
					livro.setVendedor(vendedor);

					try {
						produtoService.incluir(livro);
					} catch (ConstraintViolationException e) {
						FileLogger.logException("[Produto:Livro] " + vendedor + " - " + e.getMessage());
					}

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

					vendedor.setId(Integer.valueOf(campos[8]));
					veiculo.setVendedor(vendedor);

					try {
						produtoService.incluir(veiculo);
					} catch (ConstraintViolationException e) {
						FileLogger.logException("[Produto:Veiculo] " + vendedor + " - " + e.getMessage());
					}

					break;

				default:
					break;
			}

			linha = leitura.readLine();
		}

		for (Vendedor v : vendedorService.obterLista()) {
			for (Produto produto : produtoService.obterLista(v)) {
				System.out.println("[Produto] " + produto);
			}
		}

		leitura.close();
	}
}