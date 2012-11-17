package org.jboss.as.quickstarts.kitchensink.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jboss.as.quickstarts.kitchensink.util.TransactionScopedEM;


/**
 * <p>Implements a generic set of basic DB related operations for the entities</p>
 * 
 * @author AffanHasan
 */
@Stateless
public class BasicEntityServiceBean implements IBasicEntityService {
	
	@Inject
	@TransactionScopedEM
	private EntityManager em;

	@Override
	public <T> boolean persist(T entity) {
		try{
			em.persist(entity);
			return true;
		}catch(RuntimeException e){
			return false;
		}
	}

	@Override
	public <T> boolean update(T entity) {
		boolean emContains = em.contains(entity);
		//If entity manager do not contain this entity, then merge it in the current persistence context.
		if(!emContains){
			entity = em.merge(entity);
			return true;
		}else if(emContains){
			return true;
		}
		return false;
	}

	@Override
	public <T> boolean remove(T entity) {
		boolean emContains = em.contains(entity);
		//If entity manager do not contain this entity, then merge it in the current persistence context & put the entity in to the removed state.
		if(!emContains){
			entity = em.merge(entity);
			em.remove(entity);
			return true;
		}else if(emContains){
			em.remove(entity);
			return true;
		}
		return false;
	}

	@Override
	public <T> T getSingleInstance(Class<T> entityClass, Map<String, Object> criteriaMap) {
		
		//If no criteria is provided
		if(criteriaMap.size() == 0){
			return null;
		}
		
		//Factory for building criteria queries
		CriteriaBuilder cB = em.getCriteriaBuilder();
		
		//Criteria query definition
		CriteriaQuery<T> cQ = cB.createQuery(entityClass);
		
		//Root object : It defines a set of domain objects on which the query will be evaluated
		Root<T> root = cQ.from(entityClass);
		
		//Create a list of predicates
		List<Predicate> criteria = new ArrayList<Predicate>();
		ParameterExpression<? extends Object> p;
		for(Entry<String, Object> mapEntry : criteriaMap.entrySet()){
			p = cB.parameter(mapEntry.getValue().getClass(), mapEntry.getKey());
			criteria.add(cB.equal(root.get(mapEntry.getKey()), p));
		}
		
		//Create where clause with 'AND', for the query. For Example : 'where name = 'AffanHasan' AND department = 'IBM Portals' 
		cQ.where(cB.and(criteria.toArray(new Predicate[0])));
		
		TypedQuery<T> tQ = em.createQuery(cQ);
		
		for(Entry<String, Object> mapEntry : criteriaMap.entrySet()){
			tQ.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}
		//Execute the query & return the single entity instance & return null if not found
		try{
			return tQ.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}