package ml.canecarroarmato.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ml.canecarroarmato.model.Blog;
import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.repository.PostRepository;

@RunWith(MockitoJUnitRunner.class)
public class CrudRepositoryTest{
	
	@Mock 
	@Autowired 
	PostRepository postRepository;
	
	Post post;
	
	@Before
	public void setup() {
				
		post = new Post("titolo", "titolo", "post", "post", new Blog());
	}
	
	@After
	public void release() {
				
		post = null;
	}

	/*
	 * verifica la correttezza del modo usato per interagire col db
	 */
	@Test
	public void repositoryMustSaveAndReturnEntity() {
		
		// verifica che il salvataggio ritorna un post
		when(postRepository.save(post)).thenReturn(post);
		
		// verifica che l'accesso tramite titolo ritorna un post
		when(postRepository.findByTitle(post.getTitle())).thenReturn(post);
		
		Post savedPost = postRepository.save(post);
		
		// verifica che il salvataggio ritorna esattamente il post salvato
		assertThat(savedPost, is(equalTo(post)));
	}
}
