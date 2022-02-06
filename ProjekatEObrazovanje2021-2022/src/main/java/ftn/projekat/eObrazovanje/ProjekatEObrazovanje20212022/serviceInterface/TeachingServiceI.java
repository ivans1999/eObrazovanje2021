package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teaching;

public interface TeachingServiceI {

	public List<Teaching> findAll();
	
	public Teaching getOne(Long id);
	
	public Teaching save(Teaching te);
	
	public void delete(Long id);
	
	public List<Teaching> findByUsername(String username);
	
	Teaching findByCourseInstance(Long courseId);
}
