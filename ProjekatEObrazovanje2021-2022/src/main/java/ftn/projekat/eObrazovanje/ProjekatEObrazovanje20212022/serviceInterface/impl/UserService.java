package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.UserRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;

@Service
public class UserService implements UserServiceI {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Page<User> findAll(Pageable page) {
		return userRepository.findAll(page);
	}

	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findOneByUsername(username);
	}

	@Override
	public Long count() {
		return userRepository.count();
	}
}
