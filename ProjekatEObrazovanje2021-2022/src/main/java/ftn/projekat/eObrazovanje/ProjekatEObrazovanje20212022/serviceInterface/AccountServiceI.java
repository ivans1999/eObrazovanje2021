package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;

public interface AccountServiceI {

	public List<Account> findAll();
	
	public Account findById(Long id);
	
	public Account save(Account account);
	
	public void delete(Long id);
	
	public List<Account> findByUsername(String username);
}
