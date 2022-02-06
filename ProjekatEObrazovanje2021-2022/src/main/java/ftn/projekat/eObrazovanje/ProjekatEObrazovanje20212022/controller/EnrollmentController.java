package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.EnrollmentDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.CourseInstance;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.CourseInstanceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.EnrollmentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartStatusServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartTypeServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;

@RestController
@RequestMapping(value = "api/enrollment")
public class EnrollmentController {

	@Autowired
	private EnrollmentServiceI e;
	
	@Autowired
	private ExamPartServiceInterface examPartS;
	
	@Autowired
	private StudentServiceI s;
	
	@Autowired
	private CourseInstanceI c;
	
	@Autowired
	private ExamPartTypeServiceInterface examPartTypeS;
	
	@Autowired
	private ExamPartStatusServiceInterface examPartStatusS;
	
	@Autowired
	private ExamServiceInterface examS;
	
	@GetMapping
	public ResponseEntity<List<EnrollmentDTO>> getAll(){
		List<Enrollment> enrollments = e.findAll();
		
		List<EnrollmentDTO> endto = new ArrayList<EnrollmentDTO>();
		
		for(Enrollment e: enrollments) {
			endto.add(new EnrollmentDTO(e));
		}
		return new ResponseEntity<List<EnrollmentDTO>>(endto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnrollmentDTO> getOne(@PathVariable("id") Long id){
		Enrollment enrollment = e.findById(id);
		if(enrollment == null) {
			return new ResponseEntity<EnrollmentDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EnrollmentDTO>(new EnrollmentDTO(enrollment), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<EnrollmentDTO> update(@RequestBody EnrollmentDTO edto){
		Student student = s.findById(edto.getStudentDTO().getId());
		CourseInstance cii = c.findById(edto.getCourseInstanceDTO().getId());
		
		Enrollment enr = e.findById(edto.getId());
		if(enr == null) {
			return new ResponseEntity<EnrollmentDTO>(HttpStatus.NOT_FOUND);
		}
		enr.setStudent(student);
		enr.setCourseInstance(cii);
		enr = e.save(enr);
		return new ResponseEntity<EnrollmentDTO>(new EnrollmentDTO(enr), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<EnrollmentDTO> add(@RequestBody EnrollmentDTO edto){
		Student student = s.findById(edto.getStudentDTO().getId());
		CourseInstance cii = c.findById(edto.getCourseInstanceDTO().getId());
		Enrollment enr = new Enrollment();
		
		List<ExamPart> examParts = examPartS.findByCourseInstance(cii.getId());
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		
		for (ExamPart examPart : examParts) {
				dtos.add(new ExamPartDTO(examPart));
		}
		enr.setStudent(student);
		enr.setCourseInstance(cii);
		enr = e.save(enr);
		Exam exam = new Exam();
		exam.setEnrollment(enr);
		exam = examS.save(exam);
		for (ExamPartDTO examPartDTO : dtos) {
			ExamPartType examPartType = examPartTypeS.findById(examPartDTO.getExamPartTypeDTO().getId());
			ExamPartStatus examPartStatus = examPartStatusS.expsByCode("cr");
			ExamPart examPart = new ExamPart();
			examPart.setDate(examPartDTO.getDate());
			examPart.setLocation(examPartDTO.getLocation());
			examPart.setPoints(examPartDTO.getPoints());
			examPart.setWonPoints(0);
			examPart.setExam(exam);
			examPart.setExamPartType(examPartType);
			examPart.setExamPartStatus(examPartStatus);
			examPart.setCode(examPartDTO.getCode());
			examPartS.save(examPart);
		}
		
		return new ResponseEntity<EnrollmentDTO>(new EnrollmentDTO(enr), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{idCourseInstance}/{cardNumber}")
	public ResponseEntity<Void> delete(@PathVariable("idCourseInstance") Long idCourseInstance,@PathVariable("cardNumber") String cardNumber){
		Enrollment enr = e.findByCourseInstanceAndStudent(idCourseInstance, cardNumber);
		if(enr != null) {
			e.delete(enr.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/my-enrollments")
	public ResponseEntity<List<EnrollmentDTO>> getMyEnrollments(ModelMap model, Principal principal){
		String username = principal.getName();
		Student student = s.findByUser(username);
		
		List<Enrollment> enrollments = student.getEnrollments();
		
		List<EnrollmentDTO> endto = new ArrayList<EnrollmentDTO>();
		
		for(Enrollment e: enrollments) {
			endto.add(new EnrollmentDTO(e));
		}
		return new ResponseEntity<List<EnrollmentDTO>>(endto, HttpStatus.OK);
	}
}
