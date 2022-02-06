package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;

public class ExamDTO {
	
	private Long id;
	private Integer points;
	private Integer gradle;
	private EnrollmentDTO enrollmentDTO;
	
	public ExamDTO(Long id, Integer points, Integer gradle, EnrollmentDTO enrollmentDTO) {
		super();
		this.id = id;
		this.points = points;
		this.gradle = gradle;
		this.enrollmentDTO = enrollmentDTO;
	}

	public ExamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExamDTO(Exam exam) {
		this(exam.getId(),exam.getPoints(),exam.getGradle(),new EnrollmentDTO(exam.getEnrollment()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getGradle() {
		return gradle;
	}

	public void setGradle(Integer gradle) {
		this.gradle = gradle;
	}

	public EnrollmentDTO getEnrollmentDTO() {
		return enrollmentDTO;
	}

	public void setEnrollmentDTO(EnrollmentDTO enrollmentDTO) {
		this.enrollmentDTO = enrollmentDTO;
	}
	
}
