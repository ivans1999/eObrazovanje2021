package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "teachers")
public class Teacher extends JpaEntity{

	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "teacher")
	private List<Teaching> teching = new ArrayList<Teaching>();

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Long id, List<Teaching> teching, User user) {
		super(id);
		this.teching = teching;
		this.user = user;
	}

	public List<Teaching> getTeching() {
		return teching;
	}

	public void setTeching(List<Teaching> teching) {
		this.teching = teching;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
