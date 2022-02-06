package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.ExamPartTypeRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartTypeServiceInterface;

@Service
public class ExamPartTypeService implements ExamPartTypeServiceInterface {

	@Autowired
	ExamPartTypeRepository repository;
	
	@Override
	public List<ExamPartType> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ExamPartType findById(Long id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	@Override
	public ExamPartType save(ExamPartType examPart) {
		// TODO Auto-generated method stub
		return repository.save(examPart);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public ExamPartType findByCode(String code) {
		return repository.findOneByCode(code);
	}

}
