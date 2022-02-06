package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "accounts")
public class Account extends JpaEntity {

	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="account")
	private List<Payment> payments = new ArrayList<Payment>();
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=true)
	private Student student;

	public Account() {
		super();
		this.amount = 0.0;
	}

	public Account(Long id, Double amount, List<Payment> payments, Student student) {
		super(id);
		this.amount = amount;
		this.payments = payments;
		this.student = student;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
