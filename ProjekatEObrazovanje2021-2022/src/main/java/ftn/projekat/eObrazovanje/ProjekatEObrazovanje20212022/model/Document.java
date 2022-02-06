package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "documents")
public class Document extends JpaEntity{
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@ManyToOne
	@JoinColumn(name="type_document_id", referencedColumnName="id", nullable=false)
	private TypeDocument typeDocument;
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=false)
	private Student student;

	public Document(Long id, String title, String url, TypeDocument typeDocument, Student student) {
		super(id);
		this.title = title;
		this.url = url;
		this.typeDocument = typeDocument;
		this.student = student;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
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

	public TypeDocument getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
