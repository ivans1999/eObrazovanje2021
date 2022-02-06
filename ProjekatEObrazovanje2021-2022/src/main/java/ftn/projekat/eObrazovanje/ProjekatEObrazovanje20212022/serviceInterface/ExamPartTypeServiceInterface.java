package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;

public interface ExamPartTypeServiceInterface {

	public List<ExamPartType> findAll();
	
	public ExamPartType findById(Long id);
	
	public ExamPartType findByCode(String code);
	
	public ExamPartType save(ExamPartType examPart);
	
	public void delete(Long id);
}
