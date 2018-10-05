package fi.training.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.training.bookstore.domain.User;
import fi.training.bookstore.domain.UserRepository;

@Service
public class UserDetailServicelmpl implements UserDetailsService  {
	private final UserRepository repository;
	
	@Autowired
	public UserDetailServicelmpl(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
	    	User curruser = repository.findByUsername(username);
	        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), 
	        		AuthorityUtils.createAuthorityList(curruser.getRole()));
	        return user;
	    }   
}
