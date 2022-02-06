package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "enrollments")
public class Enrollment extends JpaEntity {
	
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="id", nullable=false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="course_instance_id", referencedColumnName="id", nullable=false)
	private CourseInstance courseInstance;
	
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "enrollment")
	private List<Exam> exams = new ArrayList<Exam>();


	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Enrollment(Long id, Student student, CourseInstance courseInstance, List<Exam> exams) {
		super(id);
		this.student = student;
		this.courseInstance = courseInstance;
		this.exams = exams;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public CourseInstance getCourseInstance() {
		return courseInstance;
	}


	public void setCourseInstance(CourseInstance courseInstance) {
		this.courseInstance = courseInstance;
	}


	public List<Exam> getExams() {
		return exams;
	}


	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	
}
