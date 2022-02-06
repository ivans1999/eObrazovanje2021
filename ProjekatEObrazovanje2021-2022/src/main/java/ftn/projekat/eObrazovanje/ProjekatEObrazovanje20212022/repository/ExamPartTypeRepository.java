package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;

public interface ExamPartTypeRepository extends JpaRepository<ExamPartType, Long> {
	ExamPartType findOneByCode(String code);
}
