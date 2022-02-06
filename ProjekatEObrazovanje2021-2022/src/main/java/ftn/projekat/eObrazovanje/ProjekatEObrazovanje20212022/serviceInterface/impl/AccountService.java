package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.AccountRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
@Service
public class AccountService implements AccountServiceI {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findById(Long id) {
		return accountRepository.getOne(id);
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public List<Account> findByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepository.findByStudent_user_username(username);
	}

}
