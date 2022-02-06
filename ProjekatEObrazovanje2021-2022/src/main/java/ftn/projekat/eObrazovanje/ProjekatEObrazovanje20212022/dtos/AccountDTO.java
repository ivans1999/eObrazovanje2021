package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;

public class AccountDTO {
	
	private Long id;
	
	private Double amount;
	
	private StudentDTO studentDTO;

	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(Long id, Double amount, StudentDTO studentDTO) {
		super();
		this.id = id;
		this.amount = amount;
		this.studentDTO = studentDTO;
	}

	public AccountDTO(Account account) {
		this(account.getId(), account.getAmount(), new StudentDTO(account.getStudent()));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public StudentDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	
}
