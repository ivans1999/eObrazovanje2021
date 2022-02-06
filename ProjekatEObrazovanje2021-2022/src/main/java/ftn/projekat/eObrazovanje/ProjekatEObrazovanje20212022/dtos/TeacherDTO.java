package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;

public class TeacherDTO {
	
	private Long id;
	
	private UserDTO userDTO;

	public TeacherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherDTO(Long id, UserDTO userDTO) {
		super();
		this.id = id;
		this.userDTO = userDTO;
	}

	public TeacherDTO(Teacher teacher) {
		this(teacher.getId(), new UserDTO(teacher.getUser()));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	

}
