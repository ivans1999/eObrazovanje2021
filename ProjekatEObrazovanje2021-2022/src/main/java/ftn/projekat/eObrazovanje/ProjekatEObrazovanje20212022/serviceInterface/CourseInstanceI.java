package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;

public interface CourseInstanceI {
	
	public Page<CourseInstance> getAll(Pageable page);
	
	public CourseInstance findById(Long id);
	
	public CourseInstance save(CourseInstance ci);
	
	public void delete(Long id);
	
	public Page<CourseInstance> findByTeacher(String username,Pageable page);
	
	public Page<CourseInstance> findByStudent(String username,Pageable page);
	
	public Long countAll();
	
	public Long countForTeacher(String username);
	
	public Long countForStudent(String username);

}
