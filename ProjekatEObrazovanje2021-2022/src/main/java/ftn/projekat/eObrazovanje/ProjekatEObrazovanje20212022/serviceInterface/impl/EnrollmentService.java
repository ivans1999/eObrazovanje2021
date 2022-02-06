package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.EnrollmentRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.EnrollmentServiceI;

@Service
public class EnrollmentService implements EnrollmentServiceI {

	@Autowired
	EnrollmentRepository er;
	
	@Override
	public List<Enrollment> findAll() {
		// TODO Auto-generated method stub
		return er.findAll();
	}

	@Override
	public Enrollment findById(Long id) {
		// TODO Auto-generated method stub
		return er.getOne(id);
	}

	@Override
	public Enrollment save(Enrollment e) {
		// TODO Auto-generated method stub
		return er.save(e);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		er.deleteById(id);
	}

	@Override
	public Enrollment findByCourseInstanceAndStudent(Long idCourseInstance, String cardNumber) {
		return er.findByCourseInstance_idAndStudent_cardNumber(idCourseInstance, cardNumber);
	}

}
