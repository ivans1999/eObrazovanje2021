package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.TeacherRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeacherServiceI;

@Service
public class TeacherService implements TeacherServiceI {

	@Autowired
	TeacherRepository teacherRepos;
	
	@Override
	public Page<Teacher> findAll(Pageable page) {
		return teacherRepos.findAll(page);
	}

	@Override
	public Teacher findById(Long id) {
		// TODO Auto-generated method stub
		return teacherRepos.getOne(id);
	}

	@Override
	public Teacher save(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherRepos.save(teacher);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		teacherRepos.deleteById(id);
	}

	@Override
	public Teacher findByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherRepos.findOneByUser_username(username);
	}

	@Override
	public Teacher findByCourse(Long courseId) {
		// TODO Auto-generated method stub
		return teacherRepos.findByCourse(courseId);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return teacherRepos.count();
	}

	@Override
	public Page<Teacher> findAll(String searchString, Pageable page) {
		// TODO Auto-generated method stub
		return teacherRepos.findAll(searchString,page);
	}

}
