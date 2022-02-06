package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.CourseSpecificationDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseSpecification;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseSpecificationI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl.CourseSpecificationService;

@RestController
@RequestMapping(value = "api/course-specfication")
public class CourseSpecificationController {

	@Autowired
	private CourseSpecificationI coursSpecifServ;
	
	@GetMapping(value = "/number-course-specification")
	public ResponseEntity<Long> getNumberPage(){
		System.out.println("\nGet number page");
		Long num = coursSpecifServ.count()/5;
		Long mod = coursSpecifServ.count()%5;
		if(mod>0) {
			num ++;
		}
		
		return new ResponseEntity<Long>(num, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CourseSpecificationDTO>> getAllCourseSpecifications(@RequestParam String searchString,Pageable page){
		Page<CourseSpecification> csspecs = coursSpecifServ.findAll(searchString,page);
		
		List<CourseSpecificationDTO> cspecsDTO = new ArrayList<CourseSpecificationDTO>();
		
		for(CourseSpecification css : csspecs) {
			cspecsDTO.add(new CourseSpecificationDTO(css));
		}
		
		return new ResponseEntity<List<CourseSpecificationDTO>>(cspecsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CourseSpecificationDTO> getOneCourseSpecification(@PathVariable("id") Long id){
		CourseSpecification cs = coursSpecifServ.findById(id);
		if(cs == null) {
			return new ResponseEntity<CourseSpecificationDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CourseSpecificationDTO>(new CourseSpecificationDTO(cs), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<CourseSpecificationDTO> updateCourseSpecification(@RequestBody CourseSpecificationDTO csDTO){
		CourseSpecification cs = coursSpecifServ.findById(csDTO.getId());
		
		if(cs == null) {
			return new ResponseEntity<CourseSpecificationDTO>(HttpStatus.NOT_FOUND);
		}
		cs.setTitle(csDTO.getTitle());
		cs.setCode(csDTO.getCode());
		cs.setEcts(csDTO.getEcts());
		coursSpecifServ.save(cs);
		return new ResponseEntity<CourseSpecificationDTO>(new CourseSpecificationDTO(cs), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<CourseSpecificationDTO> saveCourseSpecification(@RequestBody CourseSpecificationDTO csDTO){
		CourseSpecification cs = new CourseSpecification();
		cs.setTitle(csDTO.getTitle());
		cs.setCode(csDTO.getCode());
		cs.setEcts(csDTO.getEcts());
		coursSpecifServ.save(cs);
		
		return new ResponseEntity<CourseSpecificationDTO>(new CourseSpecificationDTO(cs), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteCourseSpecification(@PathVariable("id") Long id){
		CourseSpecification cs = coursSpecifServ.findById(id);
		
		if(cs != null) {
			coursSpecifServ.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
