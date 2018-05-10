package ml.canecarroarmato.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import ml.canecarroarmato.controller.BlogController;
import ml.canecarroarmato.model.Blog;
import ml.canecarroarmato.model.User;


public class BlogControllerTest {
	
	private BlogController blogController;
	private MockHttpServletRequest req;
	private MockHttpSession session;
	private User userSession;
	
	@Before
	public void setup() {
		blogController = new BlogController();
		req  = new MockHttpServletRequest();
		req.setServerPort(8080);
		
		userSession = new User();
		
		userSession.setUserid(1);
		
		session = (MockHttpSession) req.getSession();
		session.setAttribute("userSession", userSession);
	}
	
	@After
	public void release() {
		blogController = null;
		userSession    = null;
		session        = null;
		req			   = null;
	}

	@Test
	public void validateBlogCreationShouldReturnOne() {
		
		assertEquals(
				"il blog da creare appartiene all'utente della sessione e non ha già creato un blog",
				blogController.validateBlogCreation(null, userSession, 1), 
				1
		);
	}
	@Test
	public void validateBlogCreationNotSignedUpShouldReturnTwo() {
		assertEquals(
				"l'utente non è loggato",
				blogController.validateBlogCreation(null, null, 1),
				2
		);
	}
	@Test
	public void validateBlogCreationNotAuthorizedShouldReturnTwo() {
		
		assertEquals(
				"l'utente non può creare un blog per un altro utente",
				blogController.validateBlogCreation(null, userSession, 10),
				2
		);
	}
	@Test
	public void validateBlogCreationShouldReturnThree() {
		assertEquals(
				"l'utente non può creare un blog per un altro utente",
				blogController.validateBlogCreation(new Blog(), userSession, 1),
				3
		);
	}

}
