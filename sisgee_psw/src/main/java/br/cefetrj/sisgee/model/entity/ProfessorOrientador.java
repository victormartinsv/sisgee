package br.cefetrj.sisgee.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProfessorOrientador {

	@Id
	@GeneratedValue
	private Integer idProfessorOrientador;
	@Column(length = 80, nullable = false)
	private String nomeProfessorOrientador;

	// TODO Relacionamento com TermoEstagio	
	@OneToMany(mappedBy="professorOrientador")
	private List <TermoEstagio> termoEstagios;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProfessorOrientador == null) ? 0 : idProfessorOrientador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorOrientador other = (ProfessorOrientador) obj;
		if (idProfessorOrientador == null) {
			if (other.idProfessorOrientador != null)
				return false;
		} else if (!idProfessorOrientador.equals(other.idProfessorOrientador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeProfessorOrientador;
	}
}
