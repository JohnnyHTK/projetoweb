package br.gov.sp.fatec.projetoweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="flm_filme")
public class Filme extends Filmagem{
	
	@Column (name="flm_descricao", nullable = false, length = 100)
	private String descricao;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Filmagem filme;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Filmagem getFilme() {
		return filme;
	}

	public void setFilme(Filmagem filme) {
		this.filme = filme;
	}
}
