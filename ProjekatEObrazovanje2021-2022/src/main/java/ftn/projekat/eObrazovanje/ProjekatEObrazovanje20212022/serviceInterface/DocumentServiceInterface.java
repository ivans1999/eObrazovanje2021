package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;


public interface DocumentServiceInterface {

	public List<Document> findAll();
	
	public Document findById(Long id);
	
	public Document save(Document document);
	
	public void delete(Long id);
	
	public Page<Document> findByUsername(String username,Pageable page);
	
	public String saveUploadedFile(MultipartFile file) throws IOException;
	
	public Long countForStudent(String username);
}
