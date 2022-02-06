package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;

public interface ExamServiceInterface {

	public List<Exam> findAll();
	
	public Exam findById(Long id);
	
	public Exam save(Exam exam);
	
	public void delete(Long id);
	
	public List<Exam> examPassedForStudent(String cardNum);
	
	public Page<Exam> examForStudent(String cardNum,Pageable page);
	
	public List<Exam> findByCourseInstance(Long courseId);
	
	public Exam examOneByUsernameAndId(String username, Long id);
	
	public Long countForStudent(String username);
}
