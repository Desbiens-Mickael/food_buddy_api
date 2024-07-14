package fr.olprog_b.food_buddy.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet email"));
  }

}
