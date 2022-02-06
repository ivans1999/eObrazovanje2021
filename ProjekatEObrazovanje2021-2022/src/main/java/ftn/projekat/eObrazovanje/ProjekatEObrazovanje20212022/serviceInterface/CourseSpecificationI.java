package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;

public interface CourseSpecificationI {

	public Page<CourseSpecification> findAll(Pageable page);
	
	public Page<CourseSpecification> findAll(String searchString,Pageable page);
	
	public CourseSpecification findById(Long id);
	
	public CourseSpecification save(CourseSpecification courseSpecification);
	
	public void delete(Long id);
	
	public Long count();

}
