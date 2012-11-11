package org.jboss.as.quickstarts.kitchensink.test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.as.quickstarts.kitchensink.model.IBasicEntityService;
import org.jboss.as.quickstarts.kitchensink.util.TransactionScopedEM;

@Stateless
public class UserAccountServiceBean implements IBasicEntityService {
	
	@Inject
	@TransactionScopedEM
	private EntityManager em;

	@Override
	public <T> boolean register(T entity) {
		em.persist(entity);
		return true;
	}

	@Override
	public <T> boolean update(T entity) {
		if(em.contains(entity))
			return true;
		else{
			entity = em.merge(entity);
			return true;
		}
	}

	@Override
	public <T> boolean remove(T entity) {
		if(!em.contains(entity)){
			entity = em.merge(entity);
			em.remove(entity);
			return true;
		}else{
			em.remove(entity);
			return true;
		}
	}

	@Override
	public <T> T find(String... criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}