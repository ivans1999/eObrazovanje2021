package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.PaymentRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.PaymentServiceInterface;

@Service
public class PaymentService implements PaymentServiceInterface {

	@Autowired
	PaymentRepository repository;
	
	@Override
	public List<Payment> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Payment findById(Long id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	@Override
	public Payment save(Payment payment) {
		// TODO Auto-generated method stub
		return repository.save(payment);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Page<Payment> findByUsername(String username,Pageable page) {
		// TODO Auto-generated method stub
		return repository.findByAccount_student_user_username(username,page);
	}

	@Override
	public Long countByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.countByAccount_student_user_username(username);
	}

}
