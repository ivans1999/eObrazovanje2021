package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;

public class TeachingTypeDTO {

	private Long id;
	
	private String name;
	
	private String code;

	public TeachingTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeachingTypeDTO(TeachingType t) {
		this(t.getId(), t.getName(), t.getCode());
	}
	
	public TeachingTypeDTO(Long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
