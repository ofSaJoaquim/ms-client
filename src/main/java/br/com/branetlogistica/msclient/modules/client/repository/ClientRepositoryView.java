package br.com.branetlogistica.msclient.modules.client.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.StringPath;

import br.com.branetlogistica.msclient.modules.client.model.ClientView;
import br.com.branetlogistica.msclient.modules.client.model.QClientView;



@Repository
@Transactional
public interface ClientRepositoryView extends PagingAndSortingRepository<ClientView, Long>, QuerydslPredicateExecutor<ClientView> , QuerydslBinderCustomizer<QClientView> {

	   @Override
	    default public void customize(QuerydslBindings bindings, QClientView root) {
	        bindings.bind(String.class).first(
	          (StringPath path, String value) -> path.containsIgnoreCase(value));
	        
	      
	    }
	
}