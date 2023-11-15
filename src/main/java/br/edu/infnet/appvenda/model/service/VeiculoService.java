package br.edu.infnet.appvenda.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.appvenda.model.domain.Veiculo;
import br.edu.infnet.appvenda.model.repository.VeiculoRepository;

@Service
public class VeiculoService {
	@Autowired
	private VeiculoRepository veiculoRepository;

	public void incluir(Veiculo veiculo) {
		veiculoRepository.save(veiculo);
	}

	public Collection<Veiculo> obterLista() {
		return (Collection<Veiculo>) veiculoRepository.findAll();
	}

	public long obterQtde() {
		return veiculoRepository.count();
	}
}
