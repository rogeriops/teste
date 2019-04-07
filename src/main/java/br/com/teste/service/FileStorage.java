package br.com.teste.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import br.com.teste.payload.UploadFileResponse;

public interface FileStorage {

	ResponseEntity<?> storeFile(MultipartFile file);

}