package br.cefetrj.sisgee.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author padu
 *
 */
@Entity
public class TermoAditivo {
	
	@Id
	@GeneratedValue
	private Integer idTermoAditivo;
	private Date dataFimTermoAditivo;
	@Column(nullable = false)
	private Integer cargaHorariaTermoAditivo;
	@Column( nullable = false)
	private float valorBolsaTermoAditivo;
	@Column(length = 255, nullable = false)
	private String enderecoTermoAditivo;
	@Column(length = 10, nullable = false)
	private String numeroEnderecoTermoAditivo;
	@Column(length = 150, nullable = false)
	private String complementoEnderecoTermoAditivo;
	@Column(length = 150, nullable = false)
	private String bairroEnderecoTermoAditivo;
	@Column(length = 15, nullable = false)
	private String cepEnderecoTermoAditivo;
	@Column(length = 150, nullable = false)
	private String cidadeEnderecoTermoAditivo;
	@Column(length = 2, nullable = false)
	private String estadoEnderecoTermoAditivo;
	
	//TODO relacionamento TermoEstagio
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTermoAditivo == null) ? 0 : idTermoAditivo.hashCode());
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
		TermoAditivo other = (TermoAditivo) obj;
		if (idTermoAditivo == null) {
			if (other.idTermoAditivo != null)
				return false;
		} else if (!idTermoAditivo.equals(other.idTermoAditivo))
			return false;
		return true;
	}

}
