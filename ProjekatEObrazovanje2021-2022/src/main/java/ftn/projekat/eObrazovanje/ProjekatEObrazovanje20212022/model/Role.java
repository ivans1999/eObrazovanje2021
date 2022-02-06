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
@Table(name = "role")
public class Role extends JpaEntity{

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "role")
	private List<UserRole> users = new ArrayList<UserRole>();

	public Role() {
		super();
	}

	public Role(Long id, String name, String code, List<UserRole> users) {
		super(id);
		this.name = name;
		this.code = code;
		this.users = users;
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

	public List<UserRole> getUserRoles() {
		return users;
	}

	public void setUserRoles(List<UserRole> users) {
		this.users = users;
	}
}
