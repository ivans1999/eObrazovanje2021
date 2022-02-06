package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import org.springframework.web.multipart.MultipartFile;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;

public class DocumentDTO {

	private Long id;
	private String title;
	private String url;
	private TypeDocumentDTO typeDocumentDTO;
	private StudentDTO studentDTO;
	
	public DocumentDTO(Long id,String title, String url, TypeDocumentDTO typeDocumentDTO, StudentDTO studentDTO) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.typeDocumentDTO = typeDocumentDTO;
		this.studentDTO = studentDTO;
	}

	public DocumentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DocumentDTO(Document document) {
		this(document.getId(),document.getTitle(),document.getUrl(),new TypeDocumentDTO(document.getTypeDocument()),new StudentDTO(document.getStudent()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TypeDocumentDTO getTypeDocumentDTO() {
		return typeDocumentDTO;
	}

	public void setTypeDocumentDTO(TypeDocumentDTO typeDocumentDTO) {
		this.typeDocumentDTO = typeDocumentDTO;
	}

	public StudentDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}
}
