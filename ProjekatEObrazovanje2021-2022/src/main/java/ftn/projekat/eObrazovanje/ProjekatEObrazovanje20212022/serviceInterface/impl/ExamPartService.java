package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.ExamPartRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartServiceInterface;

@Service
@Transactional
public class ExamPartService implements ExamPartServiceInterface {
	
	@Autowired
	ExamPartRepository examPartRepository;
	
	@Override
	public List<ExamPart> findAll() {
		// TODO Auto-generated method stub
		return examPartRepository.findAll();
	}
	
	@Override
	public List<ExamPart> findByCode(String code) {
		// TODO Auto-generated method stub
		return examPartRepository.findByCode(code);
	}

	@Override
	public ExamPart findById(Long id) {
		// TODO Auto-generated method stub
		return examPartRepository.getOne(id);
	}

	@Override
	public ExamPart save(ExamPart examPart) {
		// TODO Auto-generated method stub
		return examPartRepository.save(examPart);
	}

	@Override
	public void delete(Long id) {
		examPartRepository.deleteById(id);
	}

	@Override
	public List<ExamPart> examPartPassedForStudent(String cardNumber) {
		// TODO Auto-generated method stub
		return examPartRepository.findOneByExam_enrollment_student_cardNumber(cardNumber);
	}

	@Override
	public List<ExamPart> findByCodeAndCardNum(String code, String cardNum) {
		// TODO Auto-generated method stub
		return examPartRepository.findByExamPartStatus_codeAndExam_enrollment_student_cardNumber(code, cardNum);
	}

	@Override
	public Page<ExamPart> findByCardNumAndCourse(String cardNum, Long id,Pageable page) {
		// TODO Auto-generated method stub
		return examPartRepository.findByExam_enrollment_student_cardNumberAndExam_enrollment_courseInstance_id(cardNum, id,page);
	}

	@Override
	public Page<ExamPart> findByCourseInstance(Long courseId,Pageable page) {
		return examPartRepository.findByCourseInstance(courseId,page);
	}
	
	@Override
	public List<ExamPart> findByCourseInstance(Long courseId) {
		return examPartRepository.findByCourseInstance(courseId);
	}

	@Override
	public long maxId() {
		return examPartRepository.maxId();
	}
	
//	@Override
//	public boolean isIn(ExamPart examPart,List<ExamPartDTO> dtos) {
//		for (ExamPartDTO examPartDTO : dtos) {
//			System.out.println(examPart.getCode()+" = "+examPartDTO.getCode());
//			if(examPart.getCode().equals(examPartDTO.getCode())) {
//				System.out.println(examPart.getCode()+" = "+examPartDTO.getCode());
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public Page<ExamPart> findByTeacher(String username,Pageable page) {
		// TODO Auto-generated method stub
		return examPartRepository.findByTeacher(username,page);
	}

	@Override
	public Long countByStudentAndCourse(String cardNum, Long id) {
		// TODO Auto-generated method stub
		return examPartRepository.countByExam_enrollment_student_cardNumberAndExam_enrollment_courseInstance_id(cardNum, id);
	}

	@Override
	public Long countByCourseInstance(Long id) {
		// TODO Auto-generated method stub
		return  examPartRepository.countByCourseInstance(id);
	}

	@Override
	public Long countByTeacher(String username) {
		// TODO Auto-generated method stub
		return examPartRepository.countByTeacher(username);
	}

	@Override
	public void deleteByCode(String code) {
		// TODO Auto-generated method stub
		examPartRepository.deleteByCode(code);
	}

	@Override
	public Page<ExamPart> findByCodeAndStatus(String code, String status,Pageable page) {
		// TODO Auto-generated method stub
		return examPartRepository.findByCodeAndExamPartStatus_code(code, status,page);
	}

	@Override
	public Long countByExamPart(String code, String status) {
		// TODO Auto-generated method stub
		return examPartRepository.countByCodeAndExamPartStatus_code(code, status);
	}

}
