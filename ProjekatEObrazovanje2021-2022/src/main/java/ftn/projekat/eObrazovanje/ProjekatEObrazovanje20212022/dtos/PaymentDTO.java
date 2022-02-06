package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import java.util.Date;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;

public class PaymentDTO {

	private Long id;
	private String currency;
	private Double amount;
	private Date date;
	private Boolean urgently;
	private String note;
	private AccountDTO accountDTO;
	
	public PaymentDTO(Long id, String currency, Double amount, Date date, Boolean urgently, String note,
			AccountDTO accountDTO) {
		super();
		this.id = id;
		this.currency = currency;
		this.amount = amount;
		this.date = date;
		this.urgently = urgently;
		this.note = note;
		this.accountDTO = accountDTO;
	}

	public PaymentDTO() {
		super();
	}
	
	public PaymentDTO(Payment p) {
		this(p.getId(),p.getCurrency(),p.getAmount(),p.getDatePayment(),p.getUrgently(),p.getNote(),new AccountDTO(p.getAccount()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}
	
}
