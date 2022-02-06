package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "course_instance")
public class CourseInstance extends JpaEntity{
	
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "course_specification_id", referencedColumnName = "id",  nullable = false)
	private CourseSpecification courseSpecification;
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "courseInstance")
	private List<Teaching> teching = new ArrayList<Teaching>();
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "courseInstance")
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();
	
	public CourseInstance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseInstance(Long id, Date startDate, Date endDate, CourseSpecification courseSpecification,
			List<Teaching> teching, List<Enrollment> enrollments) {
		super(id);
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseSpecification = courseSpecification;
		this.teching = teching;
		this.enrollments = enrollments;
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

	public CourseSpecification getCourseSpecification() {
		return courseSpecification;
	}

	public void setCourseSpecification(CourseSpecification courseSpecification) {
		this.courseSpecification = courseSpecification;
	}

	public List<Teaching> getTeching() {
		return teching;
	}

	public void setTeching(List<Teaching> teching) {
		this.teching = teching;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	@Override
	public String toString() {
		return "CourseInstance [startDate=" + startDate + ", endDate=" + endDate + ", courseSpecification="
				+ courseSpecification + ", teching=" + teching + ", enrollments=" + enrollments + "]";
	}
}
