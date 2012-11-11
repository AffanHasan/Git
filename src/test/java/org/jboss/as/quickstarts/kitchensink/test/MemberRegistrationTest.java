package org.jboss.as.quickstarts.kitchensink.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.kitchensink.controller.MemberRegistration;
import org.jboss.as.quickstarts.kitchensink.model.FBUser;
import org.jboss.as.quickstarts.kitchensink.model.IBasicEntityService;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.model.RegistrationType;
import org.jboss.as.quickstarts.kitchensink.model.User;
import org.jboss.as.quickstarts.kitchensink.model.cBook.ContactsBook;
import org.jboss.as.quickstarts.kitchensink.model.cBook.ContactsGroup;
import org.jboss.as.quickstarts.kitchensink.util.Resources;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sun.tools.doclets.internal.toolkit.util.Group;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {
   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Member.class, MemberRegistration.class, Resources.class,
            User.class, FBUser.class, RegistrationType.class, IBasicEntityService.class, UserAccountServiceBean.class
            ,ContactsBook.class, ContactsGroup.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // Deploy our test datasource
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
   }

   @Inject
   MemberRegistration memberRegistration;
   
   @Inject
   IBasicEntityService userAccountService;
   
   @Inject
   Logger log;

   @Test
   public void testRegister() throws Exception {
//      assertNotNull(newMember.getId());
	   
	   User user = new User();
	   user.setName("Affan Hasan");
	   user.setEmail("affan_hasan@hotmail.com");
	   user.setPassword("123");
	   user.setCountry("PK");
	   user.setLanguage("en");
	   
	   //Create Contact Book
	   ContactsBook cbUser = new ContactsBook();
	   cbUser.setUser(user);
	   
	   user.setContactsBook(cbUser);
	   log.info(userAccountService.register(user) == true ?
	   "User with email: " + user.getEmail() + " persisted" : "Unable to persist user with email : " + user.getEmail());
	   
	   //Updating user
	   user.setName("Affan");
	   userAccountService.update(user);
	   
	   FBUser fBUser = new FBUser();
	   fBUser.setName("Affan Hasan");
	   fBUser.setCountry("pk");
	   fBUser.setLanguage("en");
	   fBUser.setEmail("affan_hasan@hotmail.com");
	   fBUser.setId(544311701L);
	   
	   ContactsBook cbFBUser = new ContactsBook();
	   cbFBUser.setfBUser(fBUser);
	   
	   fBUser.setContactsBook(cbFBUser);
	   log.info(userAccountService.register(fBUser) == true ?
	   "User with email: " + fBUser.getEmail() + " persisted" : "Unable to persist user with email : " + fBUser.getEmail());
	   
	   log.info(userAccountService.remove(user) ? "User with email: " + user.getEmail() + " removed from DB" : 
		   "Unable to remove user with email: " + user.getEmail());
	   
	   log.info(userAccountService.remove(fBUser) ? "User with email: " + fBUser.getEmail() + " removed from DB" : 
		   "Unable to remove user with email: " + fBUser.getEmail());
	   
	   log.info("To be or not to be, thatz the question");
   }
}