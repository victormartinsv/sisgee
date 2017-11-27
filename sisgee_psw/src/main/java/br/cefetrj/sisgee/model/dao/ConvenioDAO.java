package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;

public class ConvenioDAO extends GenericDAO<Convenio> {
	
	public ConvenioDAO() {
		super(Convenio.class, PersistenceManager.getEntityManager());
	}
	
	public Convenio buscarByNumero(String numero){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE :numero")
		    .setParameter("numero", numero)
		    .getSingleResult();
	}
}
