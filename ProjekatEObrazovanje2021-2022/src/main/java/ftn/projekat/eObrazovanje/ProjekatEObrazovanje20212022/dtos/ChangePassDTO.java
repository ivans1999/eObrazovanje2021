package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

public class ChangePassDTO {

	private String firstName;
	private String lastName;
	private String userName;
	private String oldPass;
	private String newPass;
	public ChangePassDTO() {
		super();
	}
	public ChangePassDTO(String firstName, String lastName, String userName, String oldPass, String newPass) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.oldPass = oldPass;
		this.newPass = newPass;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "ChangePassDTO [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", oldPass=" + oldPass + ", newPass=" + newPass + "]";
	}
	
}
