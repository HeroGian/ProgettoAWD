package ml.canecarroarmato.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import ml.canecarroarmato.model.Blog;
import ml.canecarroarmato.model.Post;

public class PostModelTest{
	
	private Post post1;
	private Post post2;
	private MockHttpServletRequest req;
	
	@Before
	public void setup() {
		post1 = new Post("titolo", "sottotitolo", 
				"post", "abstract", 
				new Blog()
		);
		post2 = new Post("titolo", "sottotitolo", 
				"<img src='http://immagine-di-prova.jpg' />", 
				"abstract", 
				new Blog()
		);
		req  = new MockHttpServletRequest();
		req.setServerPort(8080);
	}
	
	@After
	public void release() {
		post1 = null;
		post2 = null;
		req   = null;
	}

	/*
	 * Se il post non contiene immagini dovrebbe ritornare l'immagine di default 
	 */
	@Test
	public void assertPostShouldReturnDefaultImage() {
		
		String result = String.format(
				"%s://%s:%d/resources/images/no-image.png", 
				req.getScheme(), 
				req.getServerName(), 
				req.getServerPort()
		);
		
		assertThat(post1.getFirstimage(req), is(result));
	}
	
	/*
	 * Se il post contiene immagini dovrebbe ritornare il path della prima
	 */
	@Test
	public void assertPostShouldReturnOwnImage() {
		
		String result = "http://immagine-di-prova.jpg";
		
		assertThat(post2.getFirstimage(req), is(result));
	}

}
