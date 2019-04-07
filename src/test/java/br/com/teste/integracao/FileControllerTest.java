package br.com.teste.integracao;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.teste.controller.FileController;
import br.com.teste.domain.Category;
import br.com.teste.repository.CategoryRepository;
import br.com.teste.service.CategoryService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileControllerTest {
	

	@Autowired
	private WebApplicationContext wac;
 
	@Autowired
    private CategoryService categoryService;

	@MockBean
	CategoryRepository categoryRepository;
	
	@InjectMocks
    private FileController fileController;
	 
	private MockMvc mockMvc;
	

    	@Before
    	public void setup() throws Exception {
    	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
     
 	  // File multipartFile = new File("products_teste_webdev_leroy.xlsx");

        
    }
	       
    	@Test
    	public void givenWac_whenServletContext_thenItProvidesGreetController() {
    	    ServletContext servletContext = wac.getServletContext();
    	     
    	    Assert.assertNotNull(servletContext);
    	  
    	    
    	}
	 
	    
	    @Test                                                                                         
	    public void givenMockingIsDoneByMockRestServiceServer_whenGetIsCalled_thenReturnsMockedObject() throws Exception{   
	       
	    	//
	    	List<Category> list = new ArrayList<Category>();
			list.add(new Category(123));
			Mockito.when(categoryRepository.findAll()).thenReturn(list);
			ResponseEntity<?> response = categoryService.findAllCategory();
	    	
	    	this.mockMvc.perform(get("/"))
	    	.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$", hasSize(1)));
                          
	      
	    	
	    	//
	    	verify(categoryRepository, times(1)).findAll();
	      
	                                                
	    }
	   /* MvcResult mvcResult = this.mockMvc.perform(get("/greet"))
	    	      .andDo(print()).andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.message").value("Hello World!!!"))
	    	      .andReturn();*/

		
	    
   
	

}
