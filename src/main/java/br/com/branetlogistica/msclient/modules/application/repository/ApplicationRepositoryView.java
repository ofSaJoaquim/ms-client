package br.com.branetlogistica.msclient.modules.application.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.StringPath;

import br.com.branetlogistica.msclient.modules.application.model.ApplicationView;
import br.com.branetlogistica.msclient.modules.application.model.QApplicationView;




@Repository
@Transactional
public interface ApplicationRepositoryView extends PagingAndSortingRepository<ApplicationView, Long>, QuerydslPredicateExecutor<ApplicationView> , QuerydslBinderCustomizer<QApplicationView> {

	   @Override
	    default public void customize(QuerydslBindings bindings, QApplicationView root) {
	        bindings.bind(String.class).first(
	          (StringPath path, String value) -> path.containsIgnoreCase(value));
	        
	      
	    }
	
}
