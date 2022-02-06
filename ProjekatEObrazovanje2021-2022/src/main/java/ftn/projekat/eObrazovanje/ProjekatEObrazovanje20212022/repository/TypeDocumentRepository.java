package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TypeDocument;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
	TypeDocument findOneByCode(String id);
}
