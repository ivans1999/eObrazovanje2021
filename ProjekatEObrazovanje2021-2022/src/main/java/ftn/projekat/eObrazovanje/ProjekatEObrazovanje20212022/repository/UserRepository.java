package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
