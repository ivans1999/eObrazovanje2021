package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	List<Administrator> findByUser_id(Long id);
	Administrator findOneByUser_username(String username);
}
