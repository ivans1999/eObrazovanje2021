package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;

public interface UserRoleServiceInterface {

	List<UserRole> findAllUserRole(Long id);
	void deleteByUser(Long id);
	void deleteUserRole(UserRole userRole);
	UserRole findUserRoleByUserAndRole(Long id,String code);
}
