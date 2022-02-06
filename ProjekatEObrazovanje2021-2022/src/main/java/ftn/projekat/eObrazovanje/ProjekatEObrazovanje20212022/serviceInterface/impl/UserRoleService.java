package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.UserRoleRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserRoleServiceInterface;

@Service
public class UserRoleService implements UserRoleServiceInterface{

	@Autowired
	UserRoleRepository repository;
	
	@Override
	public void deleteByUser(Long id) {
		repository.deleteByUser_id(id);
	}

	@Override
	public List<UserRole> findAllUserRole(Long id) {
		return repository.findUserRoleByUser_id(id);
	}

	@Override
	public void deleteUserRole(UserRole userRole) {
		repository.delete(userRole);
	}

	@Override
	public UserRole findUserRoleByUserAndRole(Long id, String code) {
		return repository.findUserRoleByUser_idAndRole_code(id, code);
	}

}
