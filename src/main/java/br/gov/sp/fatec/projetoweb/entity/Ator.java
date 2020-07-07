package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "A")
public class Ator extends Pessoa{
	
	@Column(name = "atr_fama")
	private String fama;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private Set<Filmagem> filmagemParticipadas;

	public String getFama() {
		return fama;
	}

	public void setFama(String fama) {
		this.fama = fama;
	}

	public Set<Filmagem> getFilmagemParticipadas() {
		return filmagemParticipadas;
	}

	public void setFilmagemParticipadas(Set<Filmagem> filmagemParticipadas) {
		this.filmagemParticipadas = filmagemParticipadas;
	}	
}

