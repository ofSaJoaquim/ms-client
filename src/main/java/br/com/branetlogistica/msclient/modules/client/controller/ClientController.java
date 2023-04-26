package br.com.branetlogistica.msclient.modules.client.controller;

import javax.validation.Valid;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.com.branetlogistica.msclient.modules.client.dto.ClientRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientResponse;
import br.com.branetlogistica.msclient.modules.client.model.ClientView;
import br.com.branetlogistica.msclient.modules.client.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService service;




	@GetMapping
	public ResponseEntity<Page<?>> page(@QuerydslPredicate(root = ClientView.class) Predicate predicate,
			Pageable pageable) {
		Page<?> page = service.page(predicate, pageable);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
		ClientResponse response = service.findByIdResponse(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		service.disable(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> insert(@Valid @RequestBody ClientRequest request) {
		ClientResponse response = service.insert(request);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.LOCATION, "/clients/" + response.getId());
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ClientRequest request, @PathVariable(name = "id") Long id) {
		ClientResponse response = service.update(id, request);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.LOCATION, "/clients/" + response.getId());
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
}