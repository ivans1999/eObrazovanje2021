package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.ExamPartStatusRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartStatusServiceInterface;

@Service
public class ExamPartStatusService implements ExamPartStatusServiceInterface {

	@Autowired
	ExamPartStatusRepository repository;
	
	@Override
	public List<ExamPartStatus> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ExamPartStatus findById(Long id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	@Override
	public ExamPartStatus save(ExamPartStatus examPart) {
		// TODO Auto-generated method stub
		return repository.save(examPart);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public ExamPartStatus expsByCode(String code) {
		// TODO Auto-generated method stub
		return repository.findOneByCode(code);
	}

}
