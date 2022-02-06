package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import java.util.Date;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;

public class CourseInstanceDTO {

	private Long id;
	
	private Date startDate;
	
	private Date endDate;
	
	private CourseSpecificationDTO courseSpecificationDTO;

	public CourseInstanceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CourseInstanceDTO(CourseInstance ci){
		this(ci.getId(), ci.getStartDate(), ci.getEndDate(), new CourseSpecificationDTO(ci.getCourseSpecification()));
	}

	public CourseInstanceDTO(Long id, Date startDate, Date endDate, CourseSpecificationDTO courseSpecificationDTO) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseSpecificationDTO = courseSpecificationDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CourseSpecificationDTO getCourseSpecificationDTO() {
		return courseSpecificationDTO;
	}

	public void setCourseSpecificationDTO(CourseSpecificationDTO courseSpecificationDTO) {
		this.courseSpecificationDTO = courseSpecificationDTO;
	}
}
