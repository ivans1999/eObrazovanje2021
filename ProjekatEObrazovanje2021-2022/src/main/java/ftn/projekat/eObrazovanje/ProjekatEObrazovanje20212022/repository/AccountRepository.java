package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByStudent_id(Long id);
	List<Account> findByStudent_user_username(String username);
}
