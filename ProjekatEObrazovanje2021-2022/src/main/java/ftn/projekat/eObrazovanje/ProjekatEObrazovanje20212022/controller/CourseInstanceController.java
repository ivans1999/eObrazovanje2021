package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.CourseInstanceDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.PaymentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Payment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseSpecificationI;

@RestController
@RequestMapping(value = "api/course-instance")
public class CourseInstanceController {

	@Autowired
	private CourseInstanceI ci;
	
	@Autowired
	private CourseSpecificationI cs;
	
	@GetMapping(value = "/number-course-instance")
	public ResponseEntity<Long> getNumberPage(@RequestParam String mode,@RequestParam String username){
		System.out.println("\nPoziva se number-course-instance: "+mode+" "+username);
		Long num = (long)0;
		if(mode.equals("ADMIN")) {
			num = ci.countAll()/5;
			Long mod = ci.countAll()%5;
			if(mod>0) {
				num ++;
			}
		}else if(mode.equals("TEACHER")) {
			num = ci.countForTeacher(username)/5;
			System.out.println("\nNum: "+num);
			Long mod = ci.countForTeacher(username)%5;
			System.out.println("\nMod: "+mod);
			if(mod>0) {
				num ++;
			}
		}else if(mode.equals("STUDENT")) {
			num = ci.countForStudent(username)/5;
			System.out.println("\nNum: "+num);
			Long mod = ci.countForStudent(username)%5;
			System.out.println("\nMod: "+mod);
			if(mod>0) {
				num ++;
			}
		}
		return new ResponseEntity<Long>(num, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CourseInstanceDTO>> getAll(Pageable page){
		Page<CourseInstance> cis = ci.getAll(page);
		
		List<CourseInstanceDTO> cisdto = new ArrayList<CourseInstanceDTO>();
		
		for(CourseInstance ci : cis) {
			cisdto.add(new CourseInstanceDTO(ci));
		}
		return new ResponseEntity<List<CourseInstanceDTO>>(cisdto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/for-student/{username}")
	public ResponseEntity<List<CourseInstanceDTO>> getAllByStudent(@PathVariable("username") String username,Pageable page){
		Page<CourseInstance> cis = ci.findByStudent(username, page);
		
		System.out.println("\npoyiva seeee");
		
		System.out.println("cis for student: " + cis);
		
		List<CourseInstanceDTO> cisdto = new ArrayList<CourseInstanceDTO>();
		
		for(CourseInstance ci : cis) {
			cisdto.add(new CourseInstanceDTO(ci));
		}
		return new ResponseEntity<List<CourseInstanceDTO>>(cisdto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/teacher")
	public ResponseEntity<List<CourseInstanceDTO>> getAllByTeacher(Principal principal,Pageable page){
		Page<CourseInstance> cis = ci.findByTeacher(principal.getName(),page);
		
		List<CourseInstanceDTO> cisdto = new ArrayList<CourseInstanceDTO>();
		
		for(CourseInstance ci : cis) {
			cisdto.add(new CourseInstanceDTO(ci));
		}
		return new ResponseEntity<List<CourseInstanceDTO>>(cisdto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/teacher/{username}")
	public ResponseEntity<List<CourseInstanceDTO>> getAllByTeacher(@PathVariable("username") String username,Pageable page){
		Page<CourseInstance> cis = ci.findByTeacher(username,page);
		
		List<CourseInstanceDTO> cisdto = new ArrayList<CourseInstanceDTO>();
		
		for(CourseInstance ci : cis) {
			cisdto.add(new CourseInstanceDTO(ci));
		}
		return new ResponseEntity<List<CourseInstanceDTO>>(cisdto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CourseInstanceDTO> getOne(@PathVariable("id") Long id){
		CourseInstance cis = ci.findById(id);
		if(cis == null) {
			return new ResponseEntity<CourseInstanceDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CourseInstanceDTO>(new CourseInstanceDTO(cis), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<CourseInstanceDTO> update(@RequestBody CourseInstanceDTO cid){
		CourseSpecification c = cs.findById(cid.getCourseSpecificationDTO().getId());
		
		CourseInstance cit = ci.findById(cid.getId());
		
		if(cit == null) {
			return new ResponseEntity<CourseInstanceDTO>(HttpStatus.NOT_FOUND);
		}
		cit.setStartDate(cid.getStartDate());
		cit.setEndDate(cid.getEndDate());
		cit.setCourseSpecification(c);
		cit = ci.save(cit);
		return new ResponseEntity<CourseInstanceDTO>(new CourseInstanceDTO(cit), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<CourseInstanceDTO> save(@RequestBody CourseInstanceDTO cid){
		CourseSpecification c = cs.findById(cid.getCourseSpecificationDTO().getId());
		
		CourseInstance cit = new CourseInstance();
		cit.setStartDate(cid.getStartDate());
		cit.setEndDate(cid.getEndDate());
		cit.setCourseSpecification(c);
		cit = ci.save(cit);
		return new ResponseEntity<CourseInstanceDTO>(new CourseInstanceDTO(cit), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		CourseInstance cit = ci.findById(id);
		if(cit != null) {
			ci.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}