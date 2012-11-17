package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Map;

/**
 * <p>Defines a generic set of basic cohesive business operations for the entities</p>
 * 
 * @author AffanHasan
 */
public interface IBasicEntityService {

	/**
	 * <p>Persists an Entity, passed as an argument to the DB.</p>
	 * 
	 * @return 'true' if success 'false' if failure
	 */
	public < T > boolean persist(T entity);
	
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
	 * <p>Find & returns a single entity instance from the data base, depending on the criteria passed as a map.</p>
	 * 
	 * <p>This method implements this type of query : 'SELECT U FROM USER U WHERE U.name = 'AffanHasan' AND U.department = 'IBM Portals'</p>
	 * 
	 * @param criteriaMap : <p>Provide the entity's exact field names as the key & provide the value as original data type for that field.</p>
	 * 
	 * @return Single entity instance from the data base & null if not found or unable to find.
	 */	
	public < T > T getSingleInstance(Class<T> entityClass, Map<String, Object> criteriaMap);
}