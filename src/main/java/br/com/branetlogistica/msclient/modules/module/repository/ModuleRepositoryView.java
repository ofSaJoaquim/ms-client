package br.com.branetlogistica.msclient.modules.module.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.StringPath;

import br.com.branetlogistica.msclient.modules.module.model.ModuleView;
import br.com.branetlogistica.msclient.modules.module.model.QModuleView;

@Repository
@Transactional
public interface ModuleRepositoryView extends PagingAndSortingRepository<ModuleView, Long>,
		QuerydslPredicateExecutor<ModuleView>, QuerydslBinderCustomizer<QModuleView> {

	@Override
	default public void customize(QuerydslBindings bindings, QModuleView root) {
		bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

	}

}