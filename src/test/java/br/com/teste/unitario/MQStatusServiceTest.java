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
import br.com.teste.domain.MQStatus;
import br.com.teste.repository.CategoryRepository;
import br.com.teste.repository.MQStatusRepository;
import br.com.teste.service.CategoryService;
import br.com.teste.service.MQStatusService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQStatusServiceTest {

	@MockBean
	MQStatusRepository repository;
	
	@Autowired
	MQStatusService service;
	

	@Test
	public void testFindAllStatus() {
		MQStatus mQStatus = new MQStatus();
		mQStatus.setFileName("fileNameTeste");
		Mockito.when(repository.findByFileName("fileNameTeste")).thenReturn(mQStatus);
		ResponseEntity<?> response = service.findAllStatus("fileNameTeste");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}

