package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teaching;

public interface TeachingRepository extends JpaRepository<Teaching, Long> {
	List<Teaching> findByTeacher_user_username(String username);
	Teaching findByCourseInstance_id(Long courseId);
}
