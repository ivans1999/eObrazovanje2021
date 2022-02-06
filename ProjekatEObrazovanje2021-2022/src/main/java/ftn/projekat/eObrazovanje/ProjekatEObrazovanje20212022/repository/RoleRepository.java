package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByCode(String code);
}
