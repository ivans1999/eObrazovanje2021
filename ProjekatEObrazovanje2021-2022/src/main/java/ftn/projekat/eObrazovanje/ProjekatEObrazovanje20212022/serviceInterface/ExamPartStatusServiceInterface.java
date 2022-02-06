package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;


public interface ExamPartStatusServiceInterface {

	public List<ExamPartStatus> findAll();
	
	public ExamPartStatus findById(Long id);
	
	public ExamPartStatus save(ExamPartStatus examPart);
	
	public void delete(Long id);
	
	public ExamPartStatus expsByCode(String code);
}
