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
import org.springframework.web.server.ResponseStatusException;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Enrollment;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Exam;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.EnrollmentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartStatusServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartTypeServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl.UserService;

@RestController
@RequestMapping(value = "api/exam-part")
public class ExamPartController {

	@Autowired
	private ExamPartServiceInterface examPartS;
	
	@Autowired
	private ExamServiceInterface examS;
	
	@Autowired
	private ExamPartTypeServiceInterface examPartTypeS;
	
	@Autowired
	private ExamPartStatusServiceInterface examPartStatusS;

	@Autowired
	private EnrollmentServiceI enrollmentS;
	
	@Autowired
	private StudentServiceI studServ;
	
	@Autowired
	private AccountServiceI accSevice;
	
	@GetMapping(value = "/number-exam-part")
	public ResponseEntity<Long> getNumberPage(@RequestParam String mode,@RequestParam String username, @RequestParam Long courseId,@RequestParam String code){
		System.out.println("\nPoziva se number-course-instance: ");
		Long num = (long)0;
		System.out.println("Mode: "+mode);
		System.out.println("Username: "+username);
		System.out.println("Course id: "+courseId);
		if(mode.equals("STUDENT_MY_EXAM_DETAIL")) {
			Student s = studServ.findByUser(username);
			num = examPartS.countByStudentAndCourse(s.getCardNumber(), courseId)/5;
			Long mod = examPartS.countByStudentAndCourse(s.getCardNumber(), courseId)%5;
			if(mod>0) {
				num++;
			}
		}else if(mode.equals("STUDENT_EXAM_DETAIL")) {
			Student s = studServ.findByUser(username);
			num = examPartS.countByStudentAndCourse(s.getCardNumber(), courseId)/5;
			Long mod = examPartS.countByStudentAndCourse(s.getCardNumber(), courseId)%5;
			if(mod>0) {
				num++;
			}
		}else if(mode.equals("ADMIN")) {
			num = examPartS.countByCourseInstance(courseId)/5;
			Long mod = examPartS.countByCourseInstance(courseId)%5;
			if(mod>0) {
				num++;
			}
		}else if(mode.equals("TEACHER_EXAM_DETAIL")) {
			num = examPartS.countByTeacher(username)/5;
			Long mod = examPartS.countByTeacher(username)%5;
			if(mod>0) {
				num++;
			}
		}else if(mode.equals("EXAM_PART_DETAIL")) {
			num = examPartS.countByExamPart(code,"re")/5;
			Long mod = examPartS.countByExamPart(code,"re")%5;
			if(mod>0) {
				num++;
			}
		}
		return new ResponseEntity<Long>(num, HttpStatus.OK);
	}
	
	@GetMapping(value = "/student/{courseId}")
//	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> getAllForCardNumber(@PathVariable("courseId") Long courseId,Principal principal,Pageable page){
		System.out.println("\nZa studenta");
		String name = principal.getName(); //get logged in username
		Student st = studServ.findByUser(name);
		Page<ExamPart> examParts = examPartS.findByCardNumAndCourse(st.getCardNumber(), courseId,page);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		System.out.println(examParts.getNumberOfElements());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{courseId}/{cardNumber}")
//	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> getAllForCardNumber(@PathVariable("courseId") Long courseId,@PathVariable("cardNumber") String cardNumber,Pageable page){
		System.out.println("\nZa odredjenog studenta");
		Page<ExamPart> examParts = examPartS.findByCardNumAndCourse(cardNumber, courseId,page);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		System.out.println(examParts.getNumberOfElements());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/course-instance/{courseId}")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> getAllForCourseInstance(@PathVariable("courseId") Long courseId,Pageable page){
		System.out.println("\nGet all parts for ADMINISTRATOR");
		Page<ExamPart> examParts = examPartS.findByCourseInstance(courseId,page);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		System.out.println(examParts.getNumberOfElements());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/teacher")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	public ResponseEntity<List<ExamPartDTO>> getAllForTeacher(Principal principal,Pageable page){
		System.out.println("\nGet all parts for TEACHER");
		System.out.println("Page size: "+page.getPageSize());
		System.out.println("Page number: "+page.getPageNumber());
		Page<ExamPart> examParts = examPartS.findByTeacher(principal.getName(),page);
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		System.out.println(examParts.getNumberOfElements());
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")//izmjeniti ovo kad dodje vrijeme
	public ResponseEntity<ExamPartDTO> getOneExamPart(@PathVariable("id") Long id){
		ExamPart examPart = examPartS.findById(id);
		System.out.println("\nZa administratora");
		if(examPart == null) {
			return new ResponseEntity<ExamPartDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO
				(examPart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/by-code/{code}")//izmjeniti ovo kad dodje vrijeme
	public ResponseEntity<ExamPartDTO> getOneExamPart(@PathVariable("code") String code){
		System.out.println("\nFind by code");
		List<ExamPart> examParts = examPartS.findByCode(code);
		if(examParts.size() == 0) {
			return new ResponseEntity<ExamPartDTO>(HttpStatus.NOT_FOUND);
		}
		ExamPart examPart = examParts.get(0);
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO(examPart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/registered-exam-parts/{code}")//izmjeniti ovo kad dodje vrijeme
//	api/exam-part/registered-exam-parts/{code}
//	api/exam-part/registered-exam-parts/1-1
	public ResponseEntity<List<ExamPartDTO>> getRegisteredExamParts(@PathVariable("code") String code,Pageable page){
		System.out.println("\nFind by code");
		Page<ExamPart> examParts = examPartS.findByCodeAndStatus(code,"re",page);
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		for (ExamPart examPart : examParts) {
			dtos.add(new ExamPartDTO(examPart));
		}
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> updateExamParts(@RequestBody ExamPartDTO dto){
		List<ExamPart> examParts = examPartS.findByCode(dto.getCode());
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		if(examParts.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		for (ExamPart examPart : examParts) {
			ExamPartType examPartType = examPartTypeS.findById(dto.getExamPartTypeDTO().getId());
			examPart.setDate(dto.getDate());
			examPart.setLocation(dto.getLocation());
			examPart.setPoints(dto.getPoints());
			examPart.setExamPartType(examPartType);
			
			examPart = examPartS.save(examPart);
			dtos.add(new ExamPartDTO(examPart));
		}
		
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping(value = "/one-exam-part")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> updateExamPart(@RequestBody ExamPartDTO dto){
		System.out.println("\nAzuriram examPart");
		List<ExamPart> examParts = examPartS.findByCode(dto.getCode());
		ExamPartStatus examPartStatus = examPartStatusS.expsByCode(dto.getStatusDTO().getCode());
		ExamPartType type = examPartTypeS.findByCode(dto.getExamPartTypeDTO().getCode());
		
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		if(examParts.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		for (ExamPart examPart : examParts) {
			examPart.setDate(dto.getDate());
			examPart.setLocation(dto.getLocation());
			examPart.setPoints(dto.getPoints());
			examPart.setExamPartType(type);
			
			examPart = examPartS.save(examPart);	
		}
		ExamPart examPart = examPartS.findById(dto.getId());
		examPart.setWonPoints(dto.getWonPoints());
		examPart.setExamPartStatus(examPartStatus);
		examPartS.save(examPart);
		
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping(value = "/evaluation-exam-parts")
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> updateExamParts(@RequestBody List<ExamPartDTO> dtos){
		System.out.println("\nEvoluation exam parts");
		for (ExamPartDTO dto : dtos) {
			ExamPart examPart = examPartS.findById(dto.getId());
			ExamPartStatus examPartStatus = examPartStatusS.expsByCode(dto.getStatusDTO().getCode());
			examPart.setWonPoints(dto.getWonPoints());
			examPart.setExamPartStatus(examPartStatus);
			examPartS.save(examPart);
		}
		
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
	public ResponseEntity<List<ExamPartDTO>> saveExamPart(@RequestBody ExamPartDTO dto){
		System.out.println("\n---->Save exam part<------\n");
		List<Exam> exams = examS.findByCourseInstance(dto.getExamDTO().getEnrollmentDTO().getCourseInstanceDTO().getId());
		ExamPartType examPartType = examPartTypeS.findByCode(dto.getExamPartTypeDTO().getCode());
		ExamPartStatus examPartStatus = examPartStatusS.expsByCode("cr");
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		
		long countRow = examPartS.countByCourseInstance(dto.getExamDTO().getEnrollmentDTO().getCourseInstanceDTO().getId());
		long maxId = 1;
		if(countRow != 0) {
			maxId = examPartS.maxId()+1;
		}
		
		String code = dto.getExamDTO().getEnrollmentDTO().getCourseInstanceDTO().getId()+"-"+maxId;
		for (Exam exam : exams) {
			if(exam.getGradle()>0) {
				continue;
			}
			ExamPart examPart = new ExamPart();
			examPart.setDate(dto.getDate());
			examPart.setLocation(dto.getLocation());
			examPart.setPoints(dto.getPoints());
			examPart.setWonPoints(0);
			examPart.setExam(exam);
			examPart.setExamPartType(examPartType);
			examPart.setExamPartStatus(examPartStatus);
			examPart.setCode(code);
			
			examPart = examPartS.save(examPart);
			dtos.add(new ExamPartDTO(examPart));
		}
			
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteExamPart(@PathVariable("id") Long id){
		ExamPart examPart = examPartS.findById(id);
		if(examPart != null) {
			examPartS.deleteByCode(examPart.getCode());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/my-exam-parts/{code}")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	public ResponseEntity<List<ExamPartDTO>> passedExamParts(Principal principal, @PathVariable("code") String code) {
		String name = principal.getName(); //get logged in username
		Student st = studServ.findByUser(name);
//		ExamPartStatus eps = examPartStatusS.expsByCode(code);
		List<ExamPart> examparts = examPartS.findByCodeAndCardNum(code, st.getCardNumber());
		List<ExamPartDTO> dtos = new ArrayList<ExamPartDTO>();
		for (ExamPart exampart : examparts) {
			dtos.add(new ExamPartDTO(exampart));
		}
		return new ResponseEntity<List<ExamPartDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping(value = "register-unregister-exam-part")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	public ResponseEntity<ExamPartDTO> registerMyExamPart(@RequestBody ExamPartDTO dto,Principal principal){
		ExamPart examPart = examPartS.findById(dto.getId());
		ExamPartStatus examPartStatus = examPartStatusS.expsByCode(dto.getStatusDTO().getCode());
		if(examPart == null) {
			return ResponseEntity.notFound().build();
		}
		Account acc = accSevice.findByUsername(principal.getName()).get(0);
		if(examPartStatus.getCode().equals("re")) {
			if(acc.getAmount() <= 0) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Nema dovoljno novca na računu");
			}else {
				Double amount = acc.getAmount() - 200;
				acc.setAmount(amount);
				acc = accSevice.save(acc);
			}
		}else {
			Double amount = acc.getAmount() + 200;
			acc.setAmount(amount);
			acc = accSevice.save(acc);
		}
		examPart.setExamPartStatus(examPartStatus);		
		examPart = examPartS.save(examPart);
		
		return new ResponseEntity<ExamPartDTO>(new ExamPartDTO(examPart), HttpStatus.OK);
	}
	
//	@PostMapping(value = "/register-exam-part")
//	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMINISTRATOR')")
//	public ResponseEntity<List<ExamPartDTO>> registerExamPart(@RequestBody ExamPartDTO dto){
//		List<ExamPartDTO> expdto = new ArrayList<ExamPartDTO>();
//		
////		String username = principal.getName(); 
//		Enrollment enrollment = enrollmentS.findById(dto.getExamDTO().getEnrollmentDTO().getId());
//		Exam exam = examS.findById(dto.getExamDTO().getId());
//		ExamPartType examPartType = examPartTypeS.findById(dto.getExamPartTypeDTO().getId());
//		ExamPartStatus exp = examPartStatusS.expsByCode("cr");
//		List<Enrollment> enrollments = enrollment.getCourseInstance().getEnrollments();
//		for(Enrollment e : enrollments) {
//			ExamPart exampart = new ExamPart();
//			Student st = e.getStudent();
//			enrollment.setStudent(st);
//			enrollment = enrollmentS.save(enrollment);
//			
//			exampart.setPoints(dto.getPoints());
//			exampart.setDate(dto.getDate());
//			exampart.setLocation(dto.getLocation());
//			exampart.setExam(exam);
//			exampart.setExamPartType(examPartType);
//			exampart.setExamPartStatus(exp);
//			exampart = examPartS.save(exampart);
//			exam.getExamParts().add(exampart);
//			if(exampart.getExamPartStatus().getCode().equals("pa")) {
//				exampart.getExam().setPoints();
//				examS.save(exampart.getExam());
//			}
//			
//			expdto.add(new ExamPartDTO(exampart));
//		}
//		
//		return new ResponseEntity<List<ExamPartDTO>>(expdto, HttpStatus.CREATED);
//	}
}
