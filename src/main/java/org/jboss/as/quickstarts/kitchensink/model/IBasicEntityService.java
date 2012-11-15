package org.jboss.as.quickstarts.kitchensink.model;

/**
 * <p>Defines a set of basic cohesive business operations for the entities</p>
 * 
 * @author AffanHasan
 */
public interface IBasicEntityService {

	/**
	 * <p>Persists an Entity, passed as an argument to the DB.</p>
	 * 
	 * @return 'true' if success 'false' if failure
	 */
	public < T > boolean register(T entity);
	
	/**
	 * <p>Updates an Entity, passed as an argument to the DB.</p>
	 * 
	 * @return 'true' if success 'false' if failure
	 */
	public < T > boolean update(T entity);
	
	/**
	 * <p>Removes an Entity, passed as an argument from the DB.</p>
	 * 
	 * @return 'true' if success 'false' if failure
	 */
	public < T > boolean remove(T entity);
	
	/**
	 * <p>Find & returns an entity instance from the data base, depending on the criteria passed as varargs argument.</p>
	 * 
	 * @return 'true' if success 'false' if failure
	 */	
	public < T > T find(Class<T> entityClass, String...criteria);
}