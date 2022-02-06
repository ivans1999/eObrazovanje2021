package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;

public interface CourseSpecificationRepository extends JpaRepository<CourseSpecification, Long> {

	CourseSpecification findOneByCode(String code);
	
	@Query(value = "SELECT * FROM eobrazovanje.course_specification\r\n" + 
						"where code LIKE concat('%',:searchString,'%') or title LIKE concat('%',:searchString,'%')", nativeQuery = true)
	Page<CourseSpecification> findAll(@Param("searchString") String searchString, Pageable page);
}
