package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;

public class ConvenioDAO extends GenericDAO<Convenio> {
	
	public ConvenioDAO() {
		super(Convenio.class, PersistenceManager.getEntityManager());
	}
	
	public Convenio buscarByNumeroEmpresa(String numero, Empresa emp){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE :numero AND c.empresa = :empresa")
		    .setParameter("numero", numero)
		    .setParameter("empresa", emp)
		    .getSingleResult();
	}
}
