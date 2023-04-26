package br.com.branetlogistica.msclient.modules.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.msclient.modules.application.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	public boolean existsByName(String name);
	public boolean existsByKey(String name);
	
}