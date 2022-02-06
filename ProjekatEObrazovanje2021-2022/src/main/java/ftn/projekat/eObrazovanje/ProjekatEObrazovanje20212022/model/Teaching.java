package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "teachings")
public class Teaching extends JpaEntity{

	@ManyToOne
	@JoinColumn(name = "teaching_type_id", referencedColumnName = "id", nullable = false)
	private TeachingType teachingType;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name = "course_instance_id", referencedColumnName = "id", nullable = false)
	private CourseInstance courseInstance;

	
	public Teaching() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teaching(Long id, TeachingType teachingType, Teacher teacher, CourseInstance courseInstance) {
		super(id);
		this.teachingType = teachingType;
		this.teacher = teacher;
		this.courseInstance = courseInstance;
	}

	public TeachingType getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(TeachingType teachingType) {
		this.teachingType = teachingType;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public CourseInstance getCourseInstance() {
		return courseInstance;
	}

	public void setCourseInstance(CourseInstance courseInstance) {
		this.courseInstance = courseInstance;
	}
}
