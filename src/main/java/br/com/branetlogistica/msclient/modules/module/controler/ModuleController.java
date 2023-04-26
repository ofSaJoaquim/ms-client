package br.com.branetlogistica.msclient.modules.module.controler;


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

import br.com.branetlogistica.msclient.modules.module.converter.ModuleConverter;
import br.com.branetlogistica.msclient.modules.module.dto.ModuleRequest;
import br.com.branetlogistica.msclient.modules.module.dto.ModuleResponse;
import br.com.branetlogistica.msclient.modules.module.model.Module;
import br.com.branetlogistica.msclient.modules.module.model.ModuleView;
import br.com.branetlogistica.msclient.modules.module.service.ModuleService;

@RestController
@RequestMapping("/modules")
public class ModuleController {

	@Autowired
	private ModuleService service;
	
	@Autowired
	private ModuleConverter converter;
	

	
	@GetMapping
    public ResponseEntity<Page<?>> page(@QuerydslPredicate(root = ModuleView.class) Predicate predicate, Pageable pageable) {
        Page<?> productValues = service.page(predicate, pageable);
        return new ResponseEntity<>(productValues, HttpStatus.OK);
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        ModuleResponse response = converter.toResponse(service.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
		
	@PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody ModuleRequest request) {
    	ModuleResponse response = converter.toResponse(service.insert(converter.toEntity(request)));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.LOCATION, "/modules/" + response.getId());
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }
	
	@PutMapping(path = "/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id, @Valid @RequestBody ModuleRequest request) {
		Module entity = converter.toEntity(request);
		entity.setId(id);
		ModuleResponse response = converter.toResponse(service.update(entity));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.LOCATION, "/modules/" + response.getId());
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	service.disabled(id);       
        return new ResponseEntity<>(null,  HttpStatus.CREATED);
    }
	
	
	
}
