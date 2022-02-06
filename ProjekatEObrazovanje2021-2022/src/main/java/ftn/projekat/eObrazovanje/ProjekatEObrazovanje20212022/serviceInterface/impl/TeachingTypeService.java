package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.TeachingTypeRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeachingTypeI;

@Service
public class TeachingTypeService implements TeachingTypeI {

	@Autowired
	TeachingTypeRepository teachingTypeRepos;
	
	@Override
	public List<TeachingType> findAll() {
		// TODO Auto-generated method stub
		return teachingTypeRepos.findAll();
	}

	@Override
	public TeachingType findById(Long id) {
		// TODO Auto-generated method stub
		return teachingTypeRepos.getOne(id);
	}

	@Override
	public TeachingType save(TeachingType teachingType) {
		// TODO Auto-generated method stub
		return teachingTypeRepos.save(teachingType);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		teachingTypeRepos.deleteById(id);
	}

	@Override
	public TeachingType findOneByCode(String code) {
		// TODO Auto-generated method stub
		return teachingTypeRepos.findOneByCode(code);
	}

}
