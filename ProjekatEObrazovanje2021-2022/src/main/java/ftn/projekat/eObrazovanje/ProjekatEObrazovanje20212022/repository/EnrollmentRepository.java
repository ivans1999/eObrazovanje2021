package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	List<Enrollment> findByCourseInstance_courseSpecification_code(String code);
	
	Enrollment findByCourseInstance_idAndStudent_cardNumber(Long idCourseInstance,String cardNumber);
}
