package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "exam_parts")
public class ExamPart extends JpaEntity {

	@Column(name = "date_exam_part", nullable = false)
	private Date date;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	@Column(name = "points", nullable = false)
	private Integer points;
	
	@Column(name = "won_points")
	private Integer wonPoints;
	
	@Column(name = "code")
	private String code;
	
	@ManyToOne
	@JoinColumn(name="exam_id", referencedColumnName="id", nullable=false)
	private Exam exam;
	
	@ManyToOne
	@JoinColumn(name="exam_part_type_id", referencedColumnName="id", nullable=false)
	private ExamPartType examPartType;
	
	@ManyToOne
	@JoinColumn(name="exam_part_status_id", referencedColumnName="id", nullable=false)
	private ExamPartStatus examPartStatus;

	public ExamPart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExamPart(Long id, Date date, String location, Integer points, Integer wonPoints, Exam exam,
			ExamPartType examPartType, ExamPartStatus examPartStatus) {
		super(id);
		this.date = date;
		this.location = location;
		this.points = points;
		this.wonPoints = wonPoints;
		this.exam = exam;
		this.examPartType = examPartType;
		this.examPartStatus = examPartStatus;
	}



//	public ExamPart(Long id, Date date, String location, Integer points, Exam exam, ExamPartType examPartType,
//			ExamPartStatus examPartStatus) {
//		super(id);
//		this.date = date;
//		this.location = location;
//		this.points = points;
//		this.exam = exam;
//		this.examPartType = examPartType;
//		this.examPartStatus = examPartStatus;
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public ExamPartType getExamPartType() {
		return examPartType;
	}

	public void setExamPartType(ExamPartType examPartType) {
		this.examPartType = examPartType;
	}

	public ExamPartStatus getExamPartStatus() {
		return examPartStatus;
	}

	public void setExamPartStatus(ExamPartStatus examPartStatus) {
		this.examPartStatus = examPartStatus;
	}

	public Integer getWonPoints() {
		return wonPoints;
	}

	public void setWonPoints(Integer wonPoints) {
		this.wonPoints = wonPoints;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
