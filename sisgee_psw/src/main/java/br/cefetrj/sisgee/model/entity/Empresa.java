package br.cefetrj.sisgee.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue
	private Integer idEmpresa;
	
	@Column(length=14, nullable=false, unique=true)
	private String cnpjEmpresa;
	
	@Column(length=100, nullable=false, unique=true)
	private String nomeEmpresa;
	
	//TODO verificar cardinalidade do relacionamento Empresa x Agente, criar o atributo que representa

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
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
		Empresa other = (Empresa) obj;
		if (idEmpresa == null) {
			if (other.idEmpresa != null)
				return false;
		} else if (!idEmpresa.equals(other.idEmpresa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeEmpresa;
	}
	
	
	
	
}
