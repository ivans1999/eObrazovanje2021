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
@Table(name = "teaching_type")
public class TeachingType extends JpaEntity {

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "teachingType")
	private List<Teaching> teachings = new ArrayList<Teaching>();

	public TeachingType() {
		super();
	}

	public TeachingType(Long id, String name, String code, List<Teaching> teachings) {
		super(id);
		this.name = name;
		this.code = code;
		this.teachings = teachings;
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

	public List<Teaching> getTeachings() {
		return teachings;
	}

	public void setTeachings(List<Teaching> teachings) {
		this.teachings = teachings;
	}
}
