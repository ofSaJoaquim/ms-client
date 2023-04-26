package br.com.branetlogistica.msclient.modules.application.controller;

import javax.validation.Valid;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.branetlogistica.msclient.modules.application.converter.ApplicationConverter;
import br.com.branetlogistica.msclient.modules.application.dto.ApplicationRequest;
import br.com.branetlogistica.msclient.modules.application.dto.ApplicationResponse;
import br.com.branetlogistica.msclient.modules.application.model.Application;
import br.com.branetlogistica.msclient.modules.application.service.ApplicationService;
import br.com.branetlogistica.msclient.modules.client.model.ClientView;

@RestController
@RequestMapping("/apps")
public class ApplicationController {

	@Autowired
	private ApplicationService service;
	
	@Autowired
	private ApplicationConverter converter;
	

	
	@GetMapping
    public ResponseEntity<Page<?>> page(@QuerydslPredicate(root = ClientView.class) Predicate predicate, Pageable pageable) {
        Page<?> productValues = service.page(predicate, pageable);
        return new ResponseEntity<>(productValues, HttpStatus.OK);
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        ApplicationResponse response = converter.toResponse(service.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
		
	@PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody ApplicationRequest request) {
    	ApplicationResponse response = converter.toResponse(service.insert(converter.toEntity(request)));
    	
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.LOCATION, "/apps/" + response.getId());
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }
	
	@PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ApplicationRequest request) {
		Application entity = converter.toEntity(request);
		entity.setId(id);
		ApplicationResponse response = converter.toResponse(service.update(entity));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.LOCATION, "/apps/" + response.getId());
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	service.disabled(id);       
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }
	
	
	
}