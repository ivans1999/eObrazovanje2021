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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartTypeDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartTypeServiceInterface;

@RestController
@RequestMapping(value = "api/exam-part-type")
public class ExamPartTypeController {
	
	@Autowired
	private ExamPartTypeServiceInterface typeS;
	
	@GetMapping
	public ResponseEntity<List<ExamPartTypeDTO>> getAllExamPartType(){
		List<ExamPartType> types = typeS.findAll();
		
		List<ExamPartTypeDTO> dtos = new ArrayList<ExamPartTypeDTO>();
		
		for(ExamPartType t : types) {
			dtos.add(new ExamPartTypeDTO(t));
		}
		
		return new ResponseEntity<List<ExamPartTypeDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExamPartTypeDTO> getOneTypeDocument(@PathVariable("id") Long id){
		ExamPartType t = typeS.findById(id);
		if(t == null) {
			return new ResponseEntity<ExamPartTypeDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamPartTypeDTO>(new ExamPartTypeDTO(t), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<ExamPartTypeDTO> updateTypeDocument(@RequestBody ExamPartTypeDTO dto){
		ExamPartType t = typeS.findById(dto.getId());
		
		if(t == null) {
			return new ResponseEntity<ExamPartTypeDTO>(HttpStatus.NOT_FOUND);
		}
		t.setName(dto.getName());
		t.setCode(dto.getCode());
		t = typeS.save(t);
		return new ResponseEntity<ExamPartTypeDTO>(new ExamPartTypeDTO(t), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<ExamPartTypeDTO> saveTypeDocument(@RequestBody ExamPartTypeDTO dto){
		ExamPartType t = new ExamPartType();
		t.setName(dto.getName());
		t.setCode(dto.getCode());
		
		t = typeS.save(t);
		
		return new ResponseEntity<ExamPartTypeDTO>(new ExamPartTypeDTO(t), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteTypeDocument(@PathVariable("id") Long id){
		ExamPartType t = typeS.findById(id);
		
		if(t != null) {
			typeS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
