package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.DocumentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.UrlDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TypeDocument;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.DocumentServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TypeDocumentServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

@RestController
@RequestMapping(value = "api/document")
public class DocumentController {

	@Autowired
	private DocumentServiceInterface documentS;
	
	@Autowired
	private StudentServiceI studentS;
	
	@Autowired
	private TypeDocumentServiceInterface documentTypeS;

//	@GetMapping
//	public ResponseEntity<List<DocumentDTO>> getAllDocuments(){
//		List<Document> documents = documentS.findAll();
//		
//		List<DocumentDTO> dtos = new ArrayList<DocumentDTO>();
//		
//		for (Document document : documents) {
//			dtos.add(new DocumentDTO(document));
//		}
//		return new ResponseEntity<List<DocumentDTO>>(dtos, HttpStatus.OK);
//	}

	@Autowired
	private StudentServiceI studServ;
	
	@GetMapping(value = "/number-documents")
	public ResponseEntity<Long> getNumberPage(@RequestParam String username){
		System.out.println("\nPoziva se number-course-instance: ");
		Long num = documentS.countForStudent(username)/5;
		Long mod = documentS.countForStudent(username)%5;
		if(mod>0) {
			num ++;
		}
		return new ResponseEntity<Long>(num, HttpStatus.OK);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	public ResponseEntity<List<DocumentDTO>> getAllDocumentsByStudent(Principal principal,Pageable page){
		System.out.println("\ngetAllDocumentsByStudent");
		
		Page<Document> documents = documentS.findByUsername(principal.getName(),page);
		List<DocumentDTO> dtos = new ArrayList<DocumentDTO>();
		
		for (Document document : documents) {
			dtos.add(new DocumentDTO(document));
		}
		return new ResponseEntity<List<DocumentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/for-student/{username}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<DocumentDTO>> getAllDocumentsByStudent(@PathVariable("username") String username,Pageable page){
		System.out.println("\ngetAllDocumentsByStudent");
		
		Page<Document> documents = documentS.findByUsername(username,page);
		System.out.println("pozvalo seee ovo 1!!");
		List<DocumentDTO> dtos = new ArrayList<DocumentDTO>();
		
		for (Document document : documents) {
			dtos.add(new DocumentDTO(document));
		}
		return new ResponseEntity<List<DocumentDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<DocumentDTO> getOneDocument(@PathVariable("id") Long id){
		Document document = documentS.findById(id);
		if(document == null) {
			return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DocumentDTO>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<DocumentDTO> updateDocument(@RequestBody DocumentDTO dto){
		
		Student student = studentS.findById(dto.getStudentDTO().getId());
		TypeDocument typeDocument = documentTypeS.findById(dto.getTypeDocumentDTO().getId());
		Document document = documentS.findById(dto.getId());
		if(document == null) {
			return new ResponseEntity<DocumentDTO>(HttpStatus.NOT_FOUND);
		}
		document.setTitle(dto.getTitle());
		document.setUrl(dto.getUrl());
		document.setStudent(student);
		document.setTypeDocument(typeDocument);
		document = documentS.save(document);
		return new ResponseEntity<DocumentDTO>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_STUDENT')")
	public ResponseEntity<Void> deleteDocument(@PathVariable("id") Long id){
		Document document = documentS.findById(id);
		if(document != null) {
			File file = new File(document.getUrl()); 
		    if (file.delete()) { 
		      System.out.println("Deleted the file: " + file.getName());
		    } else {
		      System.out.println("Failed to delete the file.");
		    } 
			documentS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_STUDENT')")
	public ResponseEntity<DocumentDTO> saveMyDocument(Principal principal, @RequestBody DocumentDTO dto, @RequestParam String username){
		System.out.println("\nsaveMyDocument: "+username);
		String name = principal.getName();
		if(!username.equals("undefined")) {
			name = username;
		}
		Student st = studServ.findByUser(name);
		
		TypeDocument typeDocument = documentTypeS.typeDocByCode(dto.getTypeDocumentDTO().getCode());
		
		Document document = new Document();
		document.setTitle(dto.getTitle());
		document.setUrl(dto.getUrl());
		document.setStudent(st);
		document.setTypeDocument(typeDocument);
		document = documentS.save(document);
		return new ResponseEntity<DocumentDTO>(new DocumentDTO(document), HttpStatus.CREATED);
	}
	
	@PostMapping("/add-file")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_STUDENT')")
    public ResponseEntity<UrlDTO> multiUploadFileModel(@RequestParam("file") MultipartFile file) {
		String url="";
		if(file==null) {
			System.out.println("File je null");
		}
		try {
			url = documentS.saveUploadedFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nURL: "+url);
        return new ResponseEntity<UrlDTO>(new UrlDTO(url), HttpStatus.OK);

    }
	
	@PostMapping(value = "/download")
    public  ResponseEntity<Resource> downloadDocument(@RequestParam("url") String url) throws IOException {
        File file = new File(url);
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
        InputStreamResource  resource  = new InputStreamResource (new FileInputStream(file));

        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        }
}
