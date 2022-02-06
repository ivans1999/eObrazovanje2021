package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;

public interface TeacherServiceI {

	public Page<Teacher> findAll(Pageable page);
	
	public Page<Teacher> findAll(String searchString, Pageable page);
	
	public Teacher findById(Long id);
	
	public Teacher save(Teacher teacher);
	
	public void delete(Long id);
	
	public Teacher findByUsername(String username);
	
	public Teacher findByCourse(Long courseId);
	
	public Long count();
}
