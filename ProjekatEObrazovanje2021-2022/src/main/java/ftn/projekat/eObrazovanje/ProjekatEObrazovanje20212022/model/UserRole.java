package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_role")
public class UserRole extends JpaEntity{
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
	private Role role;

	public UserRole() {
		super();
	}
	
	public UserRole(User user,Role role) {
		super();
		this.user = user;
		this.role=role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setAuthority(Role role) {
		this.role = role;
	}
}
