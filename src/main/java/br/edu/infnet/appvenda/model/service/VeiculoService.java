package br.edu.infnet.appvenda.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Veiculo;
import br.edu.infnet.appvenda.model.repository.VeiculoRepository;

@Service
public class VeiculoService {
	@Autowired
	private VeiculoRepository veiculoRepository;

	public enum OrdenarPor {
		MARCA("marca"),
		MODELO("modelo"),
		ANO("ano"),
		PRECO("preco");

		private final String text;

		OrdenarPor(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	public void incluir(Veiculo veiculo) {
		veiculoRepository.save(veiculo);
	}

	public Collection<Veiculo> obterLista() {
		return obterLista(OrdenarPor.MODELO);
	}

	public Collection<Veiculo> obterLista(OrdenarPor ordenarPor) {
		return obterLista(ordenarPor, Sort.Direction.ASC);
	}

	public Collection<Veiculo> obterLista(OrdenarPor ordenarPor, Sort.Direction direcao) {
		Comparator<Veiculo> comparador;

		if (ordenarPor == OrdenarPor.PRECO || ordenarPor == OrdenarPor.ANO) {
			comparador = Comparator.comparing(
					(Veiculo veiculo) -> {
						switch (ordenarPor) {
							case PRECO:
								return Double.valueOf(veiculo.getPreco());

							case ANO:
							default:
								return Double.valueOf(veiculo.getAno());
						}
					});
		} else {
			comparador = Comparator.comparing(
					(Veiculo veiculo) -> {
						switch (ordenarPor) {
							case MARCA:
								return veiculo.getMarca();

							case MODELO:
							default:
								return veiculo.getModelo();
						}
					});
		}

		Iterable<Veiculo> veiculos = veiculoRepository.findAll();
		return StreamSupport.stream(veiculos.spliterator(), false)
				.sorted(direcao == Sort.Direction.ASC ? comparador : comparador.reversed())
				.collect(java.util.stream.Collectors.toCollection(ArrayList<Veiculo>::new));
	}

	public Veiculo obterPorId(Integer id) {
		return veiculoRepository.findById(id).orElse(null);
	}

	public void atualizarPorId(Integer id, Veiculo veiculo) {
		veiculo.setId(id);
		veiculoRepository.save(veiculo);
	}

	public void excluir(Integer id) {
		veiculoRepository.deleteById(id);
	}

	public long obterQtde() {
		return veiculoRepository.count();
	}
}
