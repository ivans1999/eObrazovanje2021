package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teaching;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.TeachingRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeachingServiceI;

@Service
public class TeachingService implements TeachingServiceI {

	@Autowired
	TeachingRepository tr;
	
	@Override
	public List<Teaching> findAll() {
		// TODO Auto-generated method stub
		return tr.findAll();
	}

	@Override
	public Teaching getOne(Long id) {
		// TODO Auto-generated method stub
		return tr.getOne(id);
	}

	@Override
	public Teaching save(Teaching te) {
		// TODO Auto-generated method stub
		return tr.save(te);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tr.deleteById(id);
	}

	@Override
	public List<Teaching> findByUsername(String username) {
		// TODO Auto-generated method stub
		return tr.findByTeacher_user_username(username);
	}

	@Override
	public Teaching findByCourseInstance(Long courseId) {
		// TODO Auto-generated method stub
		return tr.findByCourseInstance_id(courseId);
	}

}
