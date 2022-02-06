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

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.TeachingTypeDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TeachingType;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeachingTypeI;

@RestController
@RequestMapping(value = "api/teaching-type")
public class TeachingTypeController {

	@Autowired
	private TeachingTypeI teachingTypeS;
	
	@GetMapping
	public ResponseEntity<List<TeachingTypeDTO>> getAllTeachingTypes(){
		List<TeachingType> teachTypes = teachingTypeS.findAll();
		
		List<TeachingTypeDTO> teachingTypesDTO = new ArrayList<TeachingTypeDTO>();
		
		for(TeachingType tt : teachTypes) {
			teachingTypesDTO.add(new TeachingTypeDTO(tt));
		}
		
		return new ResponseEntity<List<TeachingTypeDTO>>(teachingTypesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TeachingTypeDTO> getOneTeachingType(@PathVariable("id") Long id){
		TeachingType tt = teachingTypeS.findById(id);
		if(tt == null) {
			return new ResponseEntity<TeachingTypeDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TeachingTypeDTO>(new TeachingTypeDTO(tt), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<TeachingTypeDTO> updateTeachingType(@RequestBody TeachingTypeDTO ttDTO){
		TeachingType tt = teachingTypeS.findById(ttDTO.getId());
		
		if(tt == null) {
			return new ResponseEntity<TeachingTypeDTO>(HttpStatus.NOT_FOUND);
		}
		tt.setName(ttDTO.getName());
		tt.setCode(ttDTO.getCode());
		tt = teachingTypeS.save(tt);
		return new ResponseEntity<TeachingTypeDTO>(new TeachingTypeDTO(tt), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<TeachingTypeDTO> saveTeachingType(@RequestBody TeachingTypeDTO ttDTO){
		TeachingType tt = new TeachingType();
		tt.setName(ttDTO.getName());
		tt.setCode(ttDTO.getCode());
		
		tt = teachingTypeS.save(tt);
		
		return new ResponseEntity<TeachingTypeDTO>(new TeachingTypeDTO(tt), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteTeachingType(@PathVariable("id") Long id){
		TeachingType tt = teachingTypeS.findById(id);
		
		if(tt != null) {
			teachingTypeS.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
