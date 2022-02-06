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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.TeacherDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeacherServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;

@RestController
@RequestMapping(value = "api/teacher")
public class TeacherController {

	@Autowired
	private TeacherServiceI teacherService;
	
	@Autowired
	private UserServiceI userService;
	
	@GetMapping
	public ResponseEntity<List<TeacherDTO>> getAllTeachers(@RequestParam String searchString, Pageable page){
		System.out.println("\ngetAllTeachers");
		Page<Teacher> teachers = teacherService.findAll(searchString,page);
		
		List<TeacherDTO> teachersDTO = new ArrayList<TeacherDTO>();
		
		for (Teacher teacher : teachers) {
			teachersDTO.add(new TeacherDTO(teacher));
		}
		return new ResponseEntity<List<TeacherDTO>>(teachersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TeacherDTO> getOneTeacher(@PathVariable("id") Long id){
		Teacher teacher = teacherService.findById(id);
		if(teacher == null) {
			return new ResponseEntity<TeacherDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TeacherDTO>(new TeacherDTO(teacher), HttpStatus.OK);
	}
	
	@GetMapping(value = "/course-instance/{courseId}")
	public ResponseEntity<TeacherDTO> getOneTeacherByCourse(@PathVariable("courseId") Long courseId){
		Teacher teacher = teacherService.findByCourse(courseId);
		if(teacher == null) {
			return new ResponseEntity<TeacherDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TeacherDTO>(new TeacherDTO(teacher), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO){
		
		User user = userService.findById(teacherDTO.getUserDTO().getId());
		Teacher teacher = teacherService.findById(teacherDTO.getId());
		if(teacher == null) {
			return new ResponseEntity<TeacherDTO>(HttpStatus.NOT_FOUND);
		}
		teacher.setUser(user);
		teacherService.save(teacher);
		return new ResponseEntity<TeacherDTO>(new TeacherDTO(teacher), HttpStatus.OK);	
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO){
		User user = userService.findById(teacherDTO.getUserDTO().getId());
		Teacher teacher = new Teacher();
		teacher.setUser(user);
		teacher = teacherService.save(teacher);
		
		return new ResponseEntity<TeacherDTO>(new TeacherDTO(teacher), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id){
		Teacher teacher = teacherService.findById(id);
		if(teacher != null) {
			teacherService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
