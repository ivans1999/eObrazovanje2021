package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

public class JwtDTO {

	private String value;

	public JwtDTO(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
