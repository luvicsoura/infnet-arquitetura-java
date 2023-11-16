package br.edu.infnet.appvenda.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TLivro")
public class Livro extends Produto {
	@NotNull(message = "O autor deve ser informado.")
	@Size(min = 3, max = 150, message = "O autor deve ter entre 3 e 100 caracteres.")
	private String autor;
	@NotNull(message = "A editora deve ser informada.")
	@Size(min = 3, max = 100, message = "A editora deve ter entre 3 e 100 caracteres.")
	private String editora;
	@NotNull(message = "O título deve ser informado.")
	@Size(min = 3, max = 200, message = "O título deve ter entre 3 e 200 caracteres.")
	private String titulo;
	@NotNull(message = "O ano de publicação deve ser informado.")
	@Positive(message = "O ano deve ser maior que zero.")
	private int anoPublicacao;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	@Override
	public String toString() {
		return String.format(
				"Livro{id=%d, título=%s, preço=%.2f, ano=%d}",
				this.getCodigo(),
				titulo,
				this.getPreco(),
				anoPublicacao);
	}
}
