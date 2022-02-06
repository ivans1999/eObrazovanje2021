package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.StudentRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

@Service
public class StudentService implements StudentServiceI {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Page<Student> findAll(Pageable page) {
		return studentRepository.findAll(page);
	}

	@Override
	public Student findById(Long id) {
		return studentRepository.getOne(id);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student findByUser(String username) {
		// TODO Auto-generated method stub
		return studentRepository.findOneByUser_username(username);
	}

	@Override
	public Long maxId() {
		// TODO Auto-generated method stub
		return studentRepository.maxID();
	}

	@Override
	public Page<Student> findByCourseInstance(Long idCourseInstance,Pageable page) {
		return studentRepository.findByCourseInstance(idCourseInstance,page);
	}

	@Override
	public Page<Student> findOtherStudents(Long idCourseInstance,String searchString,Pageable page) {
		return studentRepository.findOtherStudents(idCourseInstance,searchString,page);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return studentRepository.count();
	}

	@Override
	public Long countByCourseInstance(Long idCourseInstance) {
		// TODO Auto-generated method stub
		return studentRepository.countByCourseInstance(idCourseInstance);
	}

}
