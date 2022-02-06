package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.AdministratorDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AdministratorServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;

@RestController
@RequestMapping(value = "api/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorServiceI adminService;
	
	@Autowired
	private UserServiceI userService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<AdministratorDTO>> getAllAdministrators(){
		List<Administrator> administrators = adminService.findAll();
		
		List<AdministratorDTO> administratorDTOs = new ArrayList<AdministratorDTO>();
		
		for (Administrator administrator : administrators) {
			administratorDTOs.add(new AdministratorDTO(administrator));
		}
		return new ResponseEntity<List<AdministratorDTO>>(administratorDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<AdministratorDTO> getOneAdministrator(@PathVariable("id") Long id){
		Administrator administrator = adminService.findById(id);
		if(administrator == null) {
			return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(administrator), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<AdministratorDTO> updateAdministrator(@RequestBody AdministratorDTO administratorDTO){
		
		User user = userService.findById(administratorDTO.getUserDTO().getId());
		Administrator admin = adminService.findById(administratorDTO.getId());
		if(admin == null) {
			return new ResponseEntity<AdministratorDTO>(HttpStatus.NOT_FOUND);
		}
		admin.setUser(user);
		adminService.save(admin);
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.OK);	
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<AdministratorDTO> saveAdministrator(@RequestBody AdministratorDTO administratorDTO){
		User user = userService.findById(administratorDTO.getUserDTO().getId());
		Administrator admin = new Administrator();
		admin.setUser(user);
		admin = adminService.save(admin);
		
		return new ResponseEntity<AdministratorDTO>(new AdministratorDTO(admin), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteAdministrator(@PathVariable("id") Long id){
		Administrator admin = adminService.findById(id);
		if(admin != null) {
			adminService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
