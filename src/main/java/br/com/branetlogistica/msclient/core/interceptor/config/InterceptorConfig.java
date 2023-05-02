package br.com.branetlogistica.msclient.core.interceptor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.branetlogistica.msclient.core.interceptor.service.InterceptorRequest;


@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {

	
	@Bean
	public InterceptorRequest interceptorRequest() {
		return new InterceptorRequest();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorRequest());
	}

}
