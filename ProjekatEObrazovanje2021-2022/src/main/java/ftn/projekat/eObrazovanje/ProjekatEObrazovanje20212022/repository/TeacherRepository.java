package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	Teacher findOneByUser_username(String username);
	
	@Query(value = "SELECT * FROM eobrazovanje.teachers\r\n" + 
			"where id = (SELECT teacher_id FROM eobrazovanje.teachings\r\n" + 
			"				where course_instance_id = (SELECT id FROM eobrazovanje.course_instance\r\n" + 
			"												where id = :courseId));", nativeQuery = true)
	Teacher findByCourse(@Param("courseId") Long courseId);
	
	@Query(value = "SELECT * FROM eobrazovanje.teachers\r\n" + 
			"	where user_id in (SELECT id FROM eobrazovanje.users\r\n" + 
			"			where concat(first_name,' ',last_name) LIKE concat('%',:searchString,'%') or concat(last_name,' ',first_name) LIKE concat('%',:searchString,'%') "
			+ "or username LIKE concat('%',:searchString,'%'))", nativeQuery = true)
	Page<Teacher> findAll(@Param("searchString") String searchString, Pageable page);
}
