package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;

public interface ExamPartStatusRepository extends JpaRepository<ExamPartStatus, Long> {
	ExamPartStatus findOneByCode(String code);
}
