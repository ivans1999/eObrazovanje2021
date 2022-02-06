package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "payments")
public class Payment extends JpaEntity {

	@Column(name = "currency", nullable = false)
	private String currency;
	
	@Column(name = "amount", nullable = false)
	private Double amount;
	
	@Column(name = "date_payment", nullable = false)
	private Date datePayment;
	
	@Column(name = "urgently", nullable = false)
	private Boolean urgently;
	
	@Column(name = "note", nullable = false)
	private String note;
	
	@ManyToOne
	@JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
	private Account account;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Long id, String currency, Double amount, Date datePayment, Boolean urgently, String note,
			Account account) {
		super(id);
		this.currency = currency;
		this.amount = amount;
		this.datePayment = datePayment;
		this.urgently = urgently;
		this.note = note;
		this.account = account;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public Boolean getUrgently() {
		return urgently;
	}

	public void setUrgently(Boolean urgently) {
		this.urgently = urgently;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
