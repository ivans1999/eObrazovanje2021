package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;

public interface UserServiceI {

	public Page<User> findAll(Pageable page);
	
	public User findById(Long id);
	
	public User save(User user);
	
	public void delete(Long id);
	
	public User findByUsername(String username);
	
	public Long count();
}

