package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;

public interface EnrollmentServiceI {

	public List<Enrollment> findAll();
	
	public Enrollment findById(Long id);
	
	public Enrollment save(Enrollment e);
	
	public void delete(Long id);
	
	public Enrollment findByCourseInstanceAndStudent(Long idCourseInstance,String cardNumber);
}
