package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;

public interface TeachingTypeRepository extends JpaRepository<TeachingType, Long> {
	TeachingType findOneByCode(String code);
}
