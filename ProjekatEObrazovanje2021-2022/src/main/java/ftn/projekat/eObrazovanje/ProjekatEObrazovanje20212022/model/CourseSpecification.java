package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "course_specification")
public class CourseSpecification extends JpaEntity {
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "ects", nullable = false)
	private int ects;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "courseSpecification")
	private List<CourseInstance> courseInstances = new ArrayList<CourseInstance>();
	
	public CourseSpecification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseSpecification(Long id, String title, int ects, String code, List<CourseInstance> courseInstances) {
		super(id);
		this.title = title;
		this.ects = ects;
		this.code = code;
		this.courseInstances = courseInstances;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CourseInstance> getCourseInstances() {
		return courseInstances;
	}

	public void setCourseInstances(List<CourseInstance> courseInstances) {
		this.courseInstances = courseInstances;
	}
}
