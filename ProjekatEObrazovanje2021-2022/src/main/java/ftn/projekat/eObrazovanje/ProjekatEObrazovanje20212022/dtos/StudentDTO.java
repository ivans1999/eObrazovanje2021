package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;

public class StudentDTO {

	private Long id;
	private String cardNumber;
	private UserDTO userDTO;
	
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDTO(Long id, String cardNumber, UserDTO userDTO) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.userDTO = userDTO;
	}
	
	public StudentDTO(Student student) {
		this(student.getId(),student.getCardNumber(),new UserDTO(student.getUser()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}	
}
