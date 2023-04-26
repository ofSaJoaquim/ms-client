package br.com.branetlogistica.msclient.modules.database.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.branetlogistica.msclient.core.exceptions.NotFoundException;
import br.com.branetlogistica.msclient.modules.client.model.ClientApplication;
import br.com.branetlogistica.msclient.modules.database.model.DataBase;
import br.com.branetlogistica.msclient.modules.database.repository.DataBaseRepository;


@Service
public class DataBaseService {

	@Autowired
	private DataBaseRepository repository;		

	
	public DataBase insert(DataBase  entity) {		
		return repository.save(entity);
			
	}	
	
	public void disable(Long id) {			
		DataBase entity = this.findById(id);
		entity.setDisabled(true);
		repository.save(entity);
			
	}
	
	public DataBase update(DataBase entity) {	
		this.findById(entity.getId());
		return repository.save(entity);
	}
	
		
	public DataBase findById(Long id) {
		Optional<DataBase> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Base de dados não encontrado"));
	}
	
	public void initDataBase(ClientApplication clientApplication) {
		
		if(clientApplication.getApplication().getDataBase()==null)
			return;
		
		String dataBaseName = "DB_"+clientApplication.getClient().getUUID();		
		dataBaseName = dataBaseName.toLowerCase().replace("-","_");
		
		if(clientApplication.getApplication().getDataBase()!=null) {
			String schema = clientApplication.getApplication().getName().toLowerCase().replace("-", "_");
			if(!ConnectionFactory.checaBaseDadosExiste(clientApplication.getApplication().getDataBase(),dataBaseName)) {
				ConnectionFactory.criarBaseDados(clientApplication.getApplication().getDataBase(),dataBaseName);			
			}
			if(!ConnectionFactory.checaSchemaExiste(clientApplication.getApplication().getDataBase(),schema,dataBaseName))
				ConnectionFactory.iniciarBaseDados(clientApplication.getApplication().getDataBase(),schema,dataBaseName);
			}
	}
	
}
