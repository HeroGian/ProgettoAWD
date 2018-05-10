package ml.canecarroarmato.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ml.canecarroarmato.conf.WebMvcConfig;

@WebAppConfiguration
@ContextConfiguration(classes = WebMvcConfig.class)
public class PostControllerTest extends AbstractJUnit4SpringContextTests{

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	
	/*
	 * Ritorna la view giusta e HTTP 200 
	 */
	@Test
	public void testGetPost() throws Exception {
	    this.mockMvc.perform(get("/user/{userid}/blog/post/{postid}", 1, 1))
	            .andExpect(status().isOk())
	            .andExpect(forwardedUrl("/WEB-INF/views/post.jsp"));
	}

}
