package org.jboss.as.quickstarts.kitchensink.model.cBook;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import org.jboss.as.quickstarts.kitchensink.model.FBUser;
import org.jboss.as.quickstarts.kitchensink.model.User;

/**
 * <p>Represents a 'contacts book'.</p>
 * <p>A contact book is @OneToOne related with a User, FBUser etc.</p>
 *
 * @author AffanHasan
 */
@Entity
@Table(name="contacts_book")
public class ContactsBook implements Serializable {

	private static final long serialVersionUID = -3837264140021293456L;
	
	//TODO To create entity constructor when it will become stable

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * <p>We use a bridging table for building this @OneToOne relation ship.</p>
	 * <p>Advantage of using a bridge table is that when ever we will add a new type of user it will not affect 'contacts_book'
	 * Entity in the DB; we just need to add a new relation ship mapping in this entity & that will be all!</p> 
	 */
	@OneToOne
	@JoinTable(name = "user_contact_book", joinColumns = @JoinColumn(name="contacts_book_id")
	,inverseJoinColumns = @JoinColumn(name="user_id"))
	private User user;

	/**
	 * <p>We use a bridging table for building this @OneToOne relation ship.</p>
	 * <p>Advantage of using a bridge table is that when ever we will add a new type of user it will not affect 'contacts_book'
	 * Entity in the DB; we just need to add a new relation ship mapping in this entity & that will be all!</p> 
	 */
	@OneToOne
	@JoinTable(name = "fb_user_contact_book", joinColumns = @JoinColumn(name="contacts_book_id")
	,inverseJoinColumns = @JoinColumn(name="user_id"))
	private FBUser fBUser;
	
	@OneToMany(mappedBy = "contactsBook")
	private List<ContactsGroup> group;
	
	@PostConstruct
	private void postContruct(){
		//TODO : To add a group named 'general_group' here
		this.getGroup().add(null);
	}

	/**
	 * This constructor is only to satisfy the JPA 2.0 specification; for an entity there must be a no argument public/protected constructor.
	 */
	public ContactsBook(){
		
	}
	
	public ContactsBook(User user, FBUser fBUser) {
		super();
		this.user = user;
		this.fBUser = fBUser;
	}

	//Boiler plate getters & setters
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FBUser getfBUser() {
		return fBUser;
	}

	public void setfBUser(FBUser fBUser) {
		this.fBUser = fBUser;
	}

	public List<ContactsGroup> getGroup() {
		return group;
	}

	public void setGroup(List<ContactsGroup> group) {
		this.group = group;
	}
}