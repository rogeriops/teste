package br.com.teste.unitario;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.domain.Category;
import br.com.teste.repository.CategoryRepository;
import br.com.teste.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@MockBean
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService;
	

	@Test
	public void testFindTheGreatestFromAllData() {
		List<Category> list = new ArrayList<Category>();
		list.add(new Category(123));
		Mockito.when(categoryRepository.findAll()).thenReturn(list);
		ResponseEntity<?> response = categoryService.findAllCategory();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		
		
	}
}

