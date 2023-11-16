package br.edu.infnet.appvenda.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TVeiculo")
public class Veiculo extends Produto {
	@NotNull(message = "A marca deve ser informada.")
	@Size(min = 3, max = 50, message = "A marca deve ter entre 3 e 50 caracteres.")
	private String marca;
	@NotNull(message = "O modelo deve ser informado.")
	@Size(min = 3, max = 50, message = "O modelo deve ter entre 3 e 50 caracteres.")
	private String modelo;
	@NotNull(message = "O ano de fabricação deve ser informado.")
	@Positive(message = "O ano deve ser maior que zero.")
	private int ano;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Vehiculo{" +
				"marca='" + marca + '\'' +
				", modelo='" + modelo + '\'' +
				", ano=" + ano +
				", preço=" + this.getPreco() +
				'}';
	}
}
