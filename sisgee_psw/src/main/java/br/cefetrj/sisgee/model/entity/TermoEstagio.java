package br.cefetrj.sisgee.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TermoEstagio {

	@Id
	@GeneratedValue
	private Integer idTermoEstagio;
	
	@Column(nullable = false)
	private Date dataInicioTermoEstagio;
	
	private Date dataFimTermoEstagio;
	
	private Date dataRescisaoTermoEstagio;
	@Column(length = 25, nullable = false)
	
	private String situacaoTermoEstagio;
	
	@Column(nullable = false)
	private Integer cargaHorariaTermoEstagio;
	
	@Column(nullable = false)
	private Float valorBolsa;
	
	@Column(length = 255, nullable = false)
	private String enderecoTermoEstagio;
	
	@Column(length = 10, nullable = false)
	private String numeroEnderecoTermoEstagio;
	
	@Column(length = 150, nullable = false)
	private String complementoEnderecoTermoEstagio;
	
	@Column(length = 150, nullable = false)
	private String bairroEnderecoTermoEstagio;
	
	@Column(length = 15, nullable = false)
	private String cepEnderecoTermoEstagio;
	
	@Column(length = 150, nullable = false)
	private String cidadeEnderecoTermoEstagio;
	
	@Column(length = 2, nullable = false)
	private String estadoEnderecoTermoEstagio;
	
	@Column(nullable = false)
	private Boolean estagioObrigatorio;

	//TODO Relacionamento Aluno x Convenio X Professor e TermoAditivo
	
	//TODO Getters e Setters
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTermoEstagio == null) ? 0 : idTermoEstagio.hashCode());
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
		TermoEstagio other = (TermoEstagio) obj;
		if (idTermoEstagio == null) {
			if (other.idTermoEstagio != null)
				return false;
		} else if (!idTermoEstagio.equals(other.idTermoEstagio))
			return false;
		return true;
	}

}
