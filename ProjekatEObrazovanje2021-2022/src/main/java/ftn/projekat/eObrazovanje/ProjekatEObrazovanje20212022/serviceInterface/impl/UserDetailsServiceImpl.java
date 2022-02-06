package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findOneByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
    	List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    	for (UserRole ur: user.getUserRoles()) {
    		grantedAuthorities.add(new SimpleGrantedAuthority(ur.getRole().getName()));
    	}
    	
    	//Java 1.8 way   	
    	/*List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
                .collect(Collectors.toList());*/
    	
    	return new org.springframework.security.core.userdetails.User(
    		  user.getUsername(),
    		  user.getPassword(),
    		  grantedAuthorities);
    }
  }
  
  public void changePassword(String oldPassword, String newPassword) {

		// Ocitavamo trenutno ulogovanog korisnika
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (authenticationManager != null) {
			//LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			//LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		//LOGGER.debug("Changing password for user '" + username + "'");

		User user = userRepository.findOneByUsername(username);
		if (user == null) {
		      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		    }
		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

	}

}
