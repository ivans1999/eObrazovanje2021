package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
	
	@Query(value = "SELECT * FROM eobrazovanje.course_instance\r\n" + 
			"	where id in (SELECT course_instance_id FROM eobrazovanje.teachings\r\n" + 
			"					where teacher_id in (SELECT id FROM eobrazovanje.teachers\r\n" + 
			"											where user_id in (SELECT id FROM eobrazovanje.users\r\n" + 
			"												where username LIKE :userName)))", nativeQuery = true)
	Page<CourseInstance> findByTeacher(@Param("userName") String username,Pageable page);
	
	@Query(value = "SELECT count(*) FROM eobrazovanje.course_instance\r\n" + 
			"				where id in (SELECT course_instance_id FROM eobrazovanje.teachings\r\n" + 
			"								where teacher_id in (SELECT id FROM eobrazovanje.teachers\r\n" + 
			"														where user_id in (SELECT id FROM eobrazovanje.users\r\n" + 
			"															where username LIKE :userName)))", nativeQuery = true)
	Long countForTeacher(@Param("userName") String username);
	
	@Query(value = "SELECT * FROM eobrazovanje.course_instance\r\n" + 
			"	where id in (SELECT course_instance_id FROM eobrazovanje.enrollments\r\n" + 
			"					where student_id in (SELECT id FROM eobrazovanje.students\r\n" + 
			"											where user_id in (SELECT id FROM eobrazovanje.users\r\n" + 
			"												where username LIKE :userName)))", nativeQuery = true)
	Page<CourseInstance> findByStudent(@Param("userName") String username,Pageable page);

	@Query(value = "SELECT count(*) FROM eobrazovanje.course_instance\r\n" + 
			"				where id in (SELECT course_instance_id FROM eobrazovanje.enrollments\r\n" + 
			"								where student_id in (SELECT id FROM eobrazovanje.students\r\n" + 
			"														where user_id in (SELECT id FROM eobrazovanje.users\r\n" + 
			"															where username LIKE :userName)))", nativeQuery = true)
	Long countForStudent(@Param("userName") String username);
}
