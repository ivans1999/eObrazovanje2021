package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "exams")
public class Exam extends JpaEntity {
	
	@Column(name = "points", nullable = false)
	private Integer points;
	
	@Column(name = "gradle", nullable = false)
	private Integer gradle;
	
	@ManyToOne
	@JoinColumn(name="enrollment_id", referencedColumnName="id", nullable=false)
	private Enrollment enrollment;

	@OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "exam")
	private List<ExamPart> examParts = new ArrayList<ExamPart>();

	public Exam() {
		this.points = 0;
		this.gradle = 0;
		this.enrollment = new Enrollment();
	}

	public Exam(Long id, Integer points, Integer gradle, Enrollment enrollment, List<ExamPart> examParts) {
		super(id);
		this.points = points;
		this.gradle = gradle;
		this.enrollment = enrollment;
		this.examParts = examParts;
	}

	public Integer getPoints() {
		int p = 0;
		for (ExamPart examPart : this.examParts) {
			p = p+examPart.getWonPoints();
		}
		return p;
	}

	public void setPoints() {
		this.points = this.getPoints();
	}

	public Integer getGradle() {
//		int p = this.getPoints();
//		if(p >= 51 && p < 61) {
//			this.gradle = 6;
//		}else if(p >= 61 && p < 71) {
//			this.gradle = 7;
//		}else if(p >= 71 && p < 81) {
//			this.gradle = 8;
//		}else if(p >= 81 && p < 91) {
//			this.gradle = 9;
//		}else if(p >= 91 && p <= 100) {
//			this.gradle = 10;
//		}
		return gradle;
	}

	public void setGradle(int gradle) {
		this.gradle = gradle;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public List<ExamPart> getExamParts() {
		return examParts;
	}

	public void setExamParts(List<ExamPart> examParts) {
		this.examParts = examParts;
	}
	
	
}
