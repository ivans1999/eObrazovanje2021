package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;

public interface TeachingTypeI {

	public List<TeachingType> findAll();
	
	public TeachingType findById(Long id);
	
	public TeachingType save(TeachingType teachingType);
	
	public void delete(Long id);
	
	public TeachingType findOneByCode(String code);
}
