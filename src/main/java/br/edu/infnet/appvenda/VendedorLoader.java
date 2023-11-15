package br.edu.infnet.appvenda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import jakarta.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.appvenda.model.domain.Endereco;
import br.edu.infnet.appvenda.model.domain.Vendedor;
import br.edu.infnet.appvenda.model.service.VendedorService;

@Order(1)
@Component
public class VendedorLoader implements ApplicationRunner {

	@Autowired
	private VendedorService vendedorService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("files/vendedor.txt");
		BufferedReader leitura = new BufferedReader(file);

		String linha = leitura.readLine();

		String[] campos = null;
		Collection<Vendedor> vendedoresExistentes = vendedorService.obterLista();

		while (linha != null) {
			campos = linha.split(";");

			String cpf = campos[1];

			if (vendedoresExistentes.stream().anyMatch(v -> v.getCpf().equals(cpf))) {
				continue;
			}

			Vendedor vendedor = new Vendedor();

			vendedor.setNome(campos[0]);
			vendedor.setCpf(cpf);
			vendedor.setEmail(campos[2]);
			vendedor.setEndereco(new Endereco(campos[3]));

			try {
				vendedorService.incluir(vendedor);
			} catch (ConstraintViolationException e) {
				FileLogger.logException("[VENDEDOR] " + vendedor + " - " + e.getMessage());
			}

			linha = leitura.readLine();
		}

		for (Vendedor vendedor : vendedorService.obterLista()) {
			System.out.println("[Vendedor] " + vendedor);
		}

		leitura.close();
	}
}