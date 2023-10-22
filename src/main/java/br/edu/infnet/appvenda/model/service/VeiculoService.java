package br.edu.infnet.appvenda.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Veiculo;

@Service
public class VeiculoService {
	private Map<Integer, Veiculo> mapaVeiculo = new HashMap<Integer, Veiculo>();

	public void incluir(Veiculo veiculo) {
		mapaVeiculo.put(veiculo.getCodigo(), veiculo);
	}

	public Collection<Veiculo> obterLista() {
		return mapaVeiculo.values();
	}
}
