package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "students")
public class Student extends JpaEntity{
	
	@Column(name = "card_number", nullable = false)
	private String cardNumber;

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Account> accounts = new ArrayList<Account>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="student")
	private List<Document> documents = new ArrayList<Document>();

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String cardNumber, List<Account> accounts, List<Enrollment> enrollments,
			List<Document> documents, User user) {
		super(id);
		this.cardNumber = cardNumber;
		this.accounts = accounts;
		this.enrollments = enrollments;
		this.documents = documents;
		this.user = user;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Student [cardNumber=" + cardNumber + ", accounts=" + accounts + ", enrollments=" + enrollments
				+ ", documents=" + documents + ", user=" + user + "]";
	}
	
	
}
