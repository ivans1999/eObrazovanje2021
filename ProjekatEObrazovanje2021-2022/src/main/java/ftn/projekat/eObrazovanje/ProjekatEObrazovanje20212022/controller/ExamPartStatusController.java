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
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ExamPartStatusDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.ExamPartStatusServiceInterface;

@RestController
@RequestMapping(value = "api/exam-part-status")
public class ExamPartStatusController {

	@Autowired
	private ExamPartStatusServiceInterface statusS;
	
	@GetMapping
	public ResponseEntity<List<ExamPartStatusDTO>> getAllTypeDocuments(){
		List<ExamPartStatus> types = statusS.findAll();
		
		List<ExamPartStatusDTO> dtos = new ArrayList<ExamPartStatusDTO>();
		
		for(ExamPartStatus t : types) {
			dtos.add(new ExamPartStatusDTO(t));
		}
		
		return new ResponseEntity<List<ExamPartStatusDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExamPartStatusDTO> getOneTypeDocument(@PathVariable("id") Long id){
		ExamPartStatus t = statusS.findById(id);
		if(t == null) {
			return new ResponseEntity<ExamPartStatusDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamPartStatusDTO>(new ExamPartStatusDTO(t), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<ExamPartStatusDTO> updateTypeDocument(@RequestBody ExamPartStatusDTO dto){
		ExamPartStatus t = statusS.findById(dto.getId());
		
		if(t == null) {
			return new ResponseEntity<ExamPartStatusDTO>(HttpStatus.NOT_FOUND);
		}
		t.setName(dto.getName());
		t.setCode(dto.getCode());
		t = statusS.save(t);
		return new ResponseEntity<ExamPartStatusDTO>(new ExamPartStatusDTO(t), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<ExamPartStatusDTO> saveTypeDocument(@RequestBody ExamPartStatusDTO dto){
		ExamPartStatus t = new ExamPartStatus();
		t.setName(dto.getName());
		t.setCode(dto.getCode());
		
		t = statusS.save(t);
		
		return new ResponseEntity<ExamPartStatusDTO>(new ExamPartStatusDTO(t), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteTypeDocument(@PathVariable("id") Long id){
		ExamPartStatus t = statusS.findById(id);
		
		if(t != null) {
			statusS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
