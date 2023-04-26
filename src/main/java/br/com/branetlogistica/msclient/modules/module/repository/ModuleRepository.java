package br.com.branetlogistica.msclient.modules.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.branetlogistica.msclient.modules.module.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

	public boolean existsByName(String name);

	public boolean existsByKey(String key);

	public boolean existsById(Long id);

}
