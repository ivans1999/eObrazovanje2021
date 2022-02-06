package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.CourseInstanceRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;

@Service
public class CourseInstanceService implements CourseInstanceI {

	@Autowired
	CourseInstanceRepository cir;
	
	@Override
	public Page<CourseInstance> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return cir.findAll(page);
	}

	@Override
	public CourseInstance findById(Long id) {
		// TODO Auto-generated method stub
		return cir.getOne(id);
	}

	@Override
	public CourseInstance save(CourseInstance ci) {
		// TODO Auto-generated method stub
		return cir.save(ci);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cir.deleteById(id);
	}

	@Override
	public Page<CourseInstance> findByTeacher(String username,Pageable page) {
		return cir.findByTeacher(username,page);
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return cir.count();
	}

	@Override
	public Long countForTeacher(String username) {
		// TODO Auto-generated method stub
		return cir.countForTeacher(username);
	}

	@Override
	public Page<CourseInstance> findByStudent(String username,Pageable page) {
		// TODO Auto-generated method stub
		return cir.findByStudent(username,page);
	}

	@Override
	public Long countForStudent(String username) {
		// TODO Auto-generated method stub
		return cir.countForStudent(username);
	}

}
