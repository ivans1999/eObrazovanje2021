package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teaching;

public class TeachingDTO {

	private Long id;
	
	private TeachingTypeDTO teachingTypeDTO;
	
	private TeacherDTO teacherDTO;
	
	private CourseInstanceDTO courseInstanceDTO;

	public TeachingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeachingDTO(Teaching te) {
		this(te.getId(), new TeachingTypeDTO(te.getTeachingType()), new TeacherDTO(te.getTeacher()), new CourseInstanceDTO(te.getCourseInstance()));
	}
	
	public TeachingDTO(Long id, TeachingTypeDTO teachingTypeDTO, TeacherDTO teacherDTO,
			CourseInstanceDTO courseInstanceDTO) {
		super();
		this.id = id;
		this.teachingTypeDTO = teachingTypeDTO;
		this.teacherDTO = teacherDTO;
		this.courseInstanceDTO = courseInstanceDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeachingTypeDTO getTeachingTypeDTO() {
		return teachingTypeDTO;
	}

	public void setTeachingTypeDTO(TeachingTypeDTO teachingTypeDTO) {
		this.teachingTypeDTO = teachingTypeDTO;
	}

	public TeacherDTO getTeacherDTO() {
		return teacherDTO;
	}

	public void setTeacherDTO(TeacherDTO teacherDTO) {
		this.teacherDTO = teacherDTO;
	}

	public CourseInstanceDTO getCourseInstanceDTO() {
		return courseInstanceDTO;
	}

	public void setCourseInstanceDTO(CourseInstanceDTO courseInstanceDTO) {
		this.courseInstanceDTO = courseInstanceDTO;
	}
}
