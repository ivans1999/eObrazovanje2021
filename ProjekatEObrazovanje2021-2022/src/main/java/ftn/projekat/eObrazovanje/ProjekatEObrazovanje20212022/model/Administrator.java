package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "administrators")
public class Administrator extends JpaEntity{

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrator(Long id, User user) {
		super(id);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
