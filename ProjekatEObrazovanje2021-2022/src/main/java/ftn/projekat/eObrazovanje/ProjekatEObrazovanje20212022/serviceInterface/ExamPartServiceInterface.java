package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;

public interface ExamPartServiceInterface {

	public List<ExamPart> findAll();
	
	public List<ExamPart> findByCode(String code);
	
	public Page<ExamPart> findByCodeAndStatus(String code,String status,Pageable page);
	
	public ExamPart findById(Long id);
	
	public ExamPart save(ExamPart examPart);
	
	public void delete(Long id);
	
	public void deleteByCode(String code);
	
	public List<ExamPart> examPartPassedForStudent(String cardNumber);
	
	public List<ExamPart> findByCodeAndCardNum(String code, String cardNum);
	
	public Page<ExamPart> findByCardNumAndCourse(String cardNum,Long id,Pageable page);
	
	public Page<ExamPart> findByCourseInstance(Long courseId,Pageable page);
	
	public List<ExamPart> findByCourseInstance(Long courseId);
	
	public long maxId();
	
	public Page<ExamPart> findByTeacher(String username,Pageable page);
	
//	public boolean isIn(ExamPart examPart,List<ExamPartDTO> dtos);
	
	public Long countByStudentAndCourse(String cardNum,Long id);
	public Long countByCourseInstance(Long id);
	public Long countByTeacher(String username);
	public Long countByExamPart(String code,String status);
}
