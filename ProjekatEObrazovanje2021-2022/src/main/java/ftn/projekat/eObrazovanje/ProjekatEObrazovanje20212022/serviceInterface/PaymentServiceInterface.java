package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;


public interface PaymentServiceInterface {

	public List<Payment> findAll();
	
	public Payment findById(Long id);
	
	public Payment save(Payment payment);
	
	public void delete(Long id);
	
	public Page<Payment> findByUsername(String username,Pageable page);
	
	public Long countByUsername(String username);
}
