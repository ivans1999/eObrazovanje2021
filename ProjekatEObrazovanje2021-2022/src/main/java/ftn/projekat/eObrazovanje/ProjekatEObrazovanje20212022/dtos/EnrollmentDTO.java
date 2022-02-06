package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;

public class EnrollmentDTO {

	private Long id;
	
	private StudentDTO studentDTO;
	
	private CourseInstanceDTO courseInstanceDTO;

	public EnrollmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EnrollmentDTO(Enrollment e) {
		this(e.getId(), new StudentDTO(e.getStudent()), new CourseInstanceDTO(e.getCourseInstance()));
	}

	public EnrollmentDTO(Long id, StudentDTO studentDTO, CourseInstanceDTO courseInstanceDTO) {
		super();
		this.id = id;
		this.studentDTO = studentDTO;
		this.courseInstanceDTO = courseInstanceDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	public CourseInstanceDTO getCourseInstanceDTO() {
		return courseInstanceDTO;
	}

	public void setCourseInstanceDTO(CourseInstanceDTO courseInstanceDTO) {
		this.courseInstanceDTO = courseInstanceDTO;
	}
}
