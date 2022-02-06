package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findByDatePayment(Date datePayment);
	List<Payment> findByUrgently(Boolean urgently);
	List<Payment> findByAccount_id(Long id);
	Page<Payment> findByAccount_student_user_username(String username,Pageable page);
	Long countByAccount_student_user_username(String username);
}
