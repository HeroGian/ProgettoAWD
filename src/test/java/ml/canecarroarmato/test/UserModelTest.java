package ml.canecarroarmato.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ml.canecarroarmato.exceptions.PasswordException;
import ml.canecarroarmato.model.User;

public class UserModelTest {
	
	User user;
	
	@Before
	public void setup() {
		
		user = new User();
				
		user.setPassword("passwordProva");
		user.autoEncodePassword();
	}
	@After
	public void release() {
		user = null;
	}

	/*
	 * In caso di password errata deve ritornare una eccezione 
	 */
	@Test(expected = PasswordException.class)
	public void userAuthenticateIncorrect() 
			throws PasswordException {
		
		user.authenticate("password");
		
	}
	
	/*
	 * In caso di password corretta non deve ritornare eccezioni 
	 */
	@Test(expected = Test.None.class)
	public void userAuthenticateCorrect() 
			throws PasswordException {
		
		user.authenticate("passwordProva");
	}

}
