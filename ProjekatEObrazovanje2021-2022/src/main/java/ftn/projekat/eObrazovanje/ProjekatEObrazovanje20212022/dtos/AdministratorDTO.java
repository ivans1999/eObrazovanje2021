package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;

public class AdministratorDTO {

	private Long id;
	
	private UserDTO userDTO;

	public AdministratorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministratorDTO(Long id, UserDTO userDTO) {
		super();
		this.id = id;
		this.userDTO = userDTO;
	}
	
	public AdministratorDTO(Administrator administrator) {
		this(administrator.getId(), new UserDTO(administrator.getUser()));
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
