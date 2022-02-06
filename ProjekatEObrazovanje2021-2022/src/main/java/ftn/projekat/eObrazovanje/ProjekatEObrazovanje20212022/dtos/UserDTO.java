package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import java.util.ArrayList;
import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Long id, String firstName, String lastName, String userName, String password,List<UserRole> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		for (UserRole userRole : roles) {
			this.roles.add(new RoleDTO(userRole.getRole()));
		}
	}
	
	public UserDTO(User user) {
		this(user.getId(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(), user.getUserRoles());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
	public String roleToString() {
		String roleString = "";
		for (RoleDTO role : this.roles) {
			roleString += role.getCode()+" ";
		}
		return roleString;
	}
}
