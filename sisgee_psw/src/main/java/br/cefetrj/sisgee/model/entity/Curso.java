package br.cefetrj.sisgee.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Integer idCurso;
	
	@Column(length=50, nullable=false)
	private String codigoCurso;

	@Column(length=255, nullable=false)
	private String nomeCurso;
	
	@ManyToMany(mappedBy="cursos")
	private List<Campus> campi;
	
	//TODO verificar relacionamentos (Campus e Aluno) e seus atributos
	
	@OneToMany(mappedBy="curso")
	private List<Aluno> alunos;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCurso == null) ? 0 : idCurso.hashCode());
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
		Curso other = (Curso) obj;
		if (idCurso == null) {
			if (other.idCurso != null)
				return false;
		} else if (!idCurso.equals(other.idCurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeCurso;
	}
	
	

}
