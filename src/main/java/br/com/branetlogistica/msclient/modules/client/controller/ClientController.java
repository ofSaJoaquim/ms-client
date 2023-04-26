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

import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationInfo;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientApplicationResponse;
import br.com.branetlogistica.msclient.modules.client.dto.ClientModuleRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientModuleResponse;
import br.com.branetlogistica.msclient.modules.client.dto.ClientRequest;
import br.com.branetlogistica.msclient.modules.client.dto.ClientResponse;
import br.com.branetlogistica.msclient.modules.client.model.ClientView;
import br.com.branetlogistica.msclient.modules.client.service.ClientApplicationService;
import br.com.branetlogistica.msclient.modules.client.service.ClientModuleService;
import br.com.branetlogistica.msclient.modules.client.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@Autowired
	private ClientModuleService clientModuleService;
	
	@Autowired
	private ClientApplicationService clientApplicationService;


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
	
	@GetMapping(path = "/{clientId}/modules")
	public ResponseEntity<Page<?>> pageClientModule(@PathVariable(name = "clientId") Long clientId, Pageable pageable) {
		Page<?> page = clientModuleService.page(clientId, pageable);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@GetMapping(path = "/{clientId}/modules/{moduleId}")
	public ResponseEntity<?> findClientModule(@PathVariable(name = "moduleId") Long clientId,
			@PathVariable(name = "moduleId") Long moduleId) {
		ClientModuleResponse response = clientModuleService.findByClientIdAndModuleIdResponse(clientId, moduleId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/{clientId}/modules")
	public ResponseEntity<?> addModule(@PathVariable(name = "clientId") Long clientId,
			@Valid @RequestBody ClientModuleRequest request) {
		ClientModuleResponse response = clientModuleService.insert(clientId, request);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.LOCATION, "/" + clientId + "/apps/" + response.getModule().getId());
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{clientId}/modules/{moduleId}")
    public ResponseEntity<?> deleteClientModule(@PathVariable(name = "moduleId" )Long clientId,@PathVariable(name = "moduleId")Long moduleId) {
        clientModuleService.disable(clientId, moduleId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
	
	@GetMapping(path = "/{clientId}/apps")
	public ResponseEntity<Page<?>> pageClientApplication(@PathVariable(name = "clientId") Long clientId,
			Pageable pageable) {
		Page<?> page = clientApplicationService.page(clientId, pageable);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@GetMapping(path = "/{clientId}/apps/{appId}")
	public ResponseEntity<?> findClientApplication(@PathVariable(name = "clientId") Long clientId,
			@PathVariable(name = "appId") Long appId) {
		ClientApplicationResponse response = clientApplicationService.findByClientIdAndApplicationIdResponse(clientId,
				appId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "findByUUID/{UUID}/apps/{appKey}")
	public ResponseEntity<?> findClientApplication(@PathVariable(name = "UUID") String UUID,
			@PathVariable(name = "appKey") String appKey) {
		ClientApplicationInfo info = clientApplicationService.findByClientUUIDAInfo(UUID, appKey);
		return new ResponseEntity<>(info, HttpStatus.OK);
	}

	@PostMapping(path = "/{clientId}/apps")
	public ResponseEntity<?> addApplication(@PathVariable(name = "clientId") Long clientId,
			@Valid @RequestBody ClientApplicationRequest request) {
		ClientApplicationResponse response = clientApplicationService.insert(clientId, request);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.LOCATION, "/" + clientId + "/apps/" + response.getApplication().getId());
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{clientId}/apps/{appId}")
	public ResponseEntity<?> deleteClientApplication(@PathVariable(name = "clientId") Long clientId,
			@PathVariable(name = "appId") Long appId) {
		clientApplicationService.disable(clientId, appId);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


}