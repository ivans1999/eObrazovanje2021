package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "type_document")
public class TypeDocument extends JpaEntity {

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="typeDocument")
	private List<Document> documents = new ArrayList<Document>();

	public TypeDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeDocument(Long id, String name, String code, List<Document> documents) {
		super(id);
		this.name = name;
		this.code = code;
		this.documents = documents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	
}
