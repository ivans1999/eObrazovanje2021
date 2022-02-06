package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.AdministratorRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AdministratorServiceI;

@Service
public class AdministratorService implements AdministratorServiceI {

	@Autowired
	AdministratorRepository administratorRepository;
	
	@Override
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	@Override
	public Administrator findById(Long id) {
		return administratorRepository.getOne(id);
	}

	@Override
	public Administrator save(Administrator administrator) {
		return administratorRepository.save(administrator);
	}

	@Override
	public void delete(Long id) {
		administratorRepository.deleteById(id);
	}

	@Override
	public Administrator findByUser(String username) {
		return administratorRepository.findOneByUser_username(username);
	}

}
