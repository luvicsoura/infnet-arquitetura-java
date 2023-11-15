package br.edu.infnet.appvenda.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "TProduto")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	@PositiveOrZero
	private int codigo;
	@Positive
	private float preco;
	private boolean estoque;
	@ManyToOne
	@JoinColumn(name = "idVendedor")
	private Vendedor vendedor;

	@Override
	public String toString() {
		return String.format("id (%d) - descricao (%s) - codigo (%d) - preco (%.2f) - estoque (%s) - vendedor [%s]",
				id, descricao, codigo, preco, estoque, vendedor);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public boolean isEstoque() {
		return estoque;
	}

	public void setEstoque(boolean estoque) {
		this.estoque = estoque;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}