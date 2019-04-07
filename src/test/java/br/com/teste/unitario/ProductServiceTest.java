package br.com.teste.unitario;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.com.teste.domain.Product;
import br.com.teste.repository.ProductRepository;
import br.com.teste.service.ProductService;
import br.com.teste.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@MockBean
	ProductRepository repository;
	
	@Autowired
	ProductService service;
	

	@Test
	public void testFindProductById() {
		
		Product product = new Product(1001, "teste", 0, "Produto teste", 100.00, new Category(123));
		Mockito.when(repository.findByIm(new Long(1001))).thenReturn(product);
		ResponseEntity<?> response = service.findProductById(new Long(1001));
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		
		
	}
	@Test
	public void testDeleteProductById() {
		
		Product product = new Product(1001, "teste", 0, "Produto teste", 100.00, new Category(123));
		Mockito.when(repository.findByIm(new Long(1001))).thenReturn(product);
		Mockito.doNothing().when(repository).deleteModify(new Long(1001));
		ProductService productService = Mockito.spy(new ProductServiceImpl(repository));
		ResponseEntity<?> response = productService.deleteProduct(new Long(1001));
		Mockito.verify(repository ).deleteModify(Mockito.any());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}

