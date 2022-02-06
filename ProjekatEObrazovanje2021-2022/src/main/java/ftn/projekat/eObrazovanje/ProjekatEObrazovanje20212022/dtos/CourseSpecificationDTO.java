package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;

public class CourseSpecificationDTO {

	private Long id;
	
	private String title;
	
	private Integer ects;
	
	private String code;

	public CourseSpecificationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseSpecificationDTO(CourseSpecification cs) {
		this(cs.getId(), cs.getTitle(), cs.getEcts(), cs.getCode());
	}
	
	public CourseSpecificationDTO(Long id, String title, Integer ects, String code) {
		super();
		this.id = id;
		this.title = title;
		this.ects = ects;
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getEcts() {
		return ects;
	}

	public void setEcts(Integer ects) {
		this.ects = ects;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
