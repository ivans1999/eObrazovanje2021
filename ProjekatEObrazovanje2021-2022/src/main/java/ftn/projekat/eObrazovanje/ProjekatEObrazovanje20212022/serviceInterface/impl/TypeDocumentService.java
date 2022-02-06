package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.TypeDocument;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.TypeDocumentRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TypeDocumentServiceInterface;

@Service
public class TypeDocumentService implements TypeDocumentServiceInterface {

	@Autowired
	TypeDocumentRepository typeDocumentRepository;
	
	@Override
	public List<TypeDocument> findAll() {
		// TODO Auto-generated method stub
		return typeDocumentRepository.findAll();
	}

	@Override
	public TypeDocument findById(Long id) {
		// TODO Auto-generated method stub
		return typeDocumentRepository.getOne(id);
	}

	@Override
	public TypeDocument save(TypeDocument typeDocument) {
		// TODO Auto-generated method stub
		return typeDocumentRepository.save(typeDocument);
	}

	@Override
	public void delete(Long id) {
		typeDocumentRepository.deleteById(id);
	}

	@Override
	public TypeDocument typeDocByCode(String code) {
		// TODO Auto-generated method stub
		return typeDocumentRepository.findOneByCode(code);
	}

}
