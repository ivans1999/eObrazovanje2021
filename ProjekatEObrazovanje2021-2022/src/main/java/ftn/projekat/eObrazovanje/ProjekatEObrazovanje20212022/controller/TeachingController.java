package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.security.Principal;
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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.TeachingDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teaching;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeacherServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeachingServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeachingTypeI;

@RestController
@RequestMapping(value = "api/teaching")
public class TeachingController {

	@Autowired
	private TeachingServiceI tsi;
	
	@Autowired
	private TeachingTypeI tti;
	
	@Autowired
	private TeacherServiceI ti;
	
	@Autowired
	private CourseInstanceI ci;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<TeachingDTO>> getAllByTeacher(Principal principal){
		List<Teaching> teachings = tsi.findByUsername(principal.getName());
		
		List<TeachingDTO> teachingdto = new ArrayList<TeachingDTO>();
		
		for(Teaching t : teachings) {
			teachingdto.add(new TeachingDTO(t));
		}
		return new ResponseEntity<List<TeachingDTO>>(teachingdto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TeachingDTO> getOne(@PathVariable("id") Long id){
		Teaching t = tsi.getOne(id);
		if(t == null) {
			return new ResponseEntity<TeachingDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TeachingDTO>(new TeachingDTO(t), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<TeachingDTO> update(@RequestBody TeachingDTO tdto){
		TeachingType ttp = tti.findById(tdto.getTeachingTypeDTO().getId());
		Teacher teacher = ti.findById(tdto.getTeacherDTO().getId());
		CourseInstance couins = ci.findById(tdto.getCourseInstanceDTO().getId());
		
		Teaching t = tsi.getOne(tdto.getId());
		
		if(t == null) {
			return new ResponseEntity<TeachingDTO>(HttpStatus.NOT_FOUND);
		}
		
		t.setTeacher(teacher);
		t.setTeachingType(ttp);
		t.setCourseInstance(couins);
		t = tsi.save(t);
		return new ResponseEntity<TeachingDTO>(new TeachingDTO(t), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<TeachingDTO> save(@RequestBody TeachingDTO tdto){
		System.out.println("\nSave teaching");
		Teaching teaching = tsi.findByCourseInstance(tdto.getCourseInstanceDTO().getId());
		if(teaching!=null) {
			tsi.delete(teaching.getId());
		}
		TeachingType ttp = tti.findOneByCode("lec");
		Teacher teacher = ti.findById(tdto.getTeacherDTO().getId());
		CourseInstance couins = ci.findById(tdto.getCourseInstanceDTO().getId());
		
		Teaching t = new Teaching();
		t.setTeacher(teacher);
		t.setTeachingType(ttp);
		t.setCourseInstance(couins);
		t = tsi.save(t);
		return new ResponseEntity<TeachingDTO>(new TeachingDTO(t), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		Teaching t = tsi.getOne(id);
		if(t != null) {
			tsi.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
