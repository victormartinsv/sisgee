package br.cefetrj.sisgee.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AgenteIntegracao {
	
	@Id
	@GeneratedValue
	private Integer idAgenteIntegracao;
	
	@Column(length=14, nullable=false, unique=true)
	private String cnpjAgenteIntegracao;
	
	@Column(length=100, nullable=false)
	private String nomeAgenteIntegracao;

	//TODO verificar relacionamento Agente x Empresa, criar o atributo que o representa.
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgenteIntegracao == null) ? 0 : idAgenteIntegracao.hashCode());
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
		AgenteIntegracao other = (AgenteIntegracao) obj;
		if (idAgenteIntegracao == null) {
			if (other.idAgenteIntegracao != null)
				return false;
		} else if (!idAgenteIntegracao.equals(other.idAgenteIntegracao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeAgenteIntegracao;
	}
	
	

}
