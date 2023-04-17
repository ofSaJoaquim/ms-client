package br.com.branetlogistica.msclient.core.security.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {

  /*  private final UserRepository userRepository;

    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       /* final var user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));*/

        final var simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_");

        return new User(
               "luiz@gmail.com",
               "aaaa",
                List.of(simpleGrantedAuthority)
        );
    }

}
