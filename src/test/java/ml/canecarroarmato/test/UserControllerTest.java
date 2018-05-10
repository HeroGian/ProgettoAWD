package ml.canecarroarmato.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ml.canecarroarmato.conf.WebMvcConfig;


@WebAppConfiguration
@ContextConfiguration(classes = WebMvcConfig.class)
public class UserControllerTest extends AbstractJUnit4SpringContextTests{
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
		
	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

	/*
	 * Va a testare che la risposta http su /user/{userid} sia 200
	 * e che ritorni la view corretta 
	 */
	@Test
	public void testGetUser() throws Exception {
	    this.mockMvc.perform(get("/user/{userid}", 1))
	            .andExpect(status().isOk())
	            .andExpect(forwardedUrl("/WEB-INF/views/user.jsp"));
	}
	
	/*
	 * Va a testare che la risposta http su /user/{userid} con userid non esistente
	 * sia 404 e ritorni la view di errore
	 */
	@Test
	public void testGetUser404() throws Exception {
	    this.mockMvc.perform(get("/user/{userid}", 0))
	            .andExpect(status().isNotFound());
	}
	
	/*
	 * Test della risposta AJAX per la POST di un MultiPartFile 
	 */
	@Test
	public void postAvatarSuccess() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.multipart("/user/{userid}", 1).file(
						new MockMultipartFile("file", new FileInputStream("D:\\server-uploads\\mockFile.png"))
				))
					.andDo(print());
	}

}
