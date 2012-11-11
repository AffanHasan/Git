package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.as.quickstarts.kitchensink.model.cBook.ContactsBook;

/**
 * Represents an application user
 * 
 * @author AffanHasan
 */
@Entity
@Access(AccessType.FIELD)
public class User implements Serializable{
	
	private static final long serialVersionUID = 7966562061346334686L;
	
	//TODO To create entity constructor when it will become stable
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Column(length = 40)
	@Size(min = 3, max = 40)
	private String name;
	
	@Email
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	@Column(length = 16)
	@Size(min = 3, max = 16)
	private String password;
	
	/**
	 * Language must be two character lower case string
	 * The JPA access type is set to AccessType.PROPERTY because we want explicit lower case
	 * <p>We use @Transient so that language will not be persisted twice as default access mode is @Access(AccessType.FIELD)</p>
	 */
	@Transient
	private String language;
	
	/**
	 * Country must be two character upper case string
	 * The JPA access type is set to AccessType.PROPERTY because we want explicit upper case
	 * <p>We use @Transient so that country will not be persisted twice as default access mode is @Access(AccessType.FIELD)</p>
	 */
	@Transient
	private String country;
	
	/**
	 * Bidirectional @OneToOne relation ship. We use a bridge table for building this relation ship.
	 * For reference see ContactsBook.java  
	 */
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private ContactsBook contactsBook;

	//Boiler plate getters & setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@NotEmpty
	@Column(length = 2)
	@Size(max = 2, min = 2)
	@Access(AccessType.PROPERTY)
	public String getLanguage() {
		return language.toLowerCase();
	}

	public void setLanguage(String language) {
		this.language = language.toLowerCase();
	}

	@NotEmpty
	@Column(length = 2)
	@Size(max = 2, min = 2)
	@Access(AccessType.PROPERTY)
	public String getCountry() {
		return country.toUpperCase();
	}

	public void setCountry(String country) {
		this.country = country.toUpperCase();
	}

	public ContactsBook getContactsBook() {
		return contactsBook;
	}

	public void setContactsBook(ContactsBook contactsBook) {
		this.contactsBook = contactsBook;
	}
}