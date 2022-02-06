package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;

public interface RoleServiceI {
	
	public List<Role> findAll();
	
	public Role findById(Long id);
	
	public Role save(Role userRole);
	
	public void delete(Long id);
	
	public Role findByCode(String code);
}
