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
@Table(name = "exam_part_types")
public class ExamPartType extends JpaEntity {

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "examPartType")
	private List<ExamPart> examParts = new ArrayList<ExamPart>();

	public ExamPartType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamPartType(Long id, String name, String code, List<ExamPart> examParts) {
		super(id);
		this.name = name;
		this.code = code;
		this.examParts = examParts;
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

	public List<ExamPart> getExamParts() {
		return examParts;
	}

	public void setExamParts(List<ExamPart> examParts) {
		this.examParts = examParts;
	}
}
