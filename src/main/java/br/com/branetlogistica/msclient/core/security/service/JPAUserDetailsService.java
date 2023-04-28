package br.com.branetlogistica.msclient.core.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
      return null;
    }
  

}
