package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pes_pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@AttributeOverride(name = "id", column = @Column(name = "pes_id"))
public class Pessoa extends Main{
	@Column(name = "pes_nome")
	private String nome;

	@Column(name = "pes_cpf")
	private Long cpf;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private Set<Filmagem> filmagensPaticipadas;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Set<Filmagem> getFilmagensPaticipadas() {
		return filmagensPaticipadas;
	}

	public void setFilmagensPaticipadas(Set<Filmagem> filmagensPaticipadas) {
		this.filmagensPaticipadas = filmagensPaticipadas;
	}
}
