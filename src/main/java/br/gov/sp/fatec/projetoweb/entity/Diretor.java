package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "T")
public class Diretor extends Pessoa{
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diretor", cascade = CascadeType.REMOVE)
	private Set<Filmagem> filmagensDirigidas;

	public Set<Filmagem> getFilmagensDirigidas() {
		return filmagensDirigidas;
	}

	public void setFilmagensDirigidas(Set<Filmagem> filmagensDirigidas) {
		this.filmagensDirigidas = filmagensDirigidas;
	}
}
