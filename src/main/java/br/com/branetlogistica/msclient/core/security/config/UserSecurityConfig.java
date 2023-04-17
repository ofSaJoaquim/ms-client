package br.com.branetlogistica.msclient.core.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import br.com.branetlogistica.msclient.core.security.service.KeycloakJwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService)
			throws Exception {
		http.userDetailsService(userDetailsService).csrf().disable().authorizeRequests().anyRequest().authenticated()
				.and().csrf().disable().oauth2ResourceServer().jwt()
				.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter());

		return http.build();
	}

}
