package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.RoleRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.RoleServiceI;

@Service
public class RoleService implements RoleServiceI {

	@Autowired
	RoleRepository repository;
	
	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return repository.getOne(id);
	}

	@Override
	public Role save(Role userRole) {
		return repository.save(userRole);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Role findByCode(String code) {
		return repository.findOneByCode(code);
	}

}
