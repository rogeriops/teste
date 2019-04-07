package br.com.teste.unitario;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import br.com.teste.listener.Sender;
import br.com.teste.property.FileStorageProperties;
import br.com.teste.service.FileStorage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileStorageServiceTest {

	

	@MockBean
	Sender sender;
	
	@Autowired
	FileStorage service;
	
	@MockBean
	MultipartFile file;
	
	@Autowired
	FileStorageProperties fileStorageProperties;
	
	
	Path fileStorageLocation;

	
	@Test
	public void testStoreFile() throws IOException {
		
       this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
	   Path targetLocation = this.fileStorageLocation.resolve("products_teste_webdev_leroy.xlsx");
	   MockMultipartFile multipartFile = new MockMultipartFile("file",   "products_teste_webdev_leroy.xlsx",  "text/plain",   new FileInputStream(targetLocation.toFile()));
	   Mockito.doNothing().when(sender).send("fileNameTeste");
	   ResponseEntity<?> response = service.storeFile(multipartFile);
	   assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}

