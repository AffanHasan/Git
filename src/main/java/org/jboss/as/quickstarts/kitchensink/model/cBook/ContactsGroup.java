package org.jboss.as.quickstarts.kitchensink.model.cBook;

import java.lang.Long;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * A ContactsBook is @OneToMany related with a ContactsGroup.
 * <p>In a contact book every contact is associated with a group & by default all contacts are assigned to a group named 'General Group'</p>
 * 
 * @author AffanHasan
 */
@Entity
@Table(name="contacts_group")
public class ContactsGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "contacts_book_id")
	private ContactsBook contactsBook;

	//Boiler plate getters & setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Long getId() {
		return this.id;
	}
}