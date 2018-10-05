package fi.training.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.training.bookstore.web.UserDetailServicelmpl;


	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		 private UserDetailServicelmpl userDetailsService;	 
		
		  @Override
	    protected void configure(HttpSecurity http) throws Exception {
			http
	        .authorizeRequests().antMatchers("/css/**").permitAll() 
	        .and()
	        .authorizeRequests().anyRequest().authenticated()
	        .and()
	      .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/booklist")
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
	    }

		//@Autowired
		   //public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
		     //auth
		    // .inMemoryAuthentication()
		            //.withUser("user").password("{noop}password").roles("USER").and()
		           // .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
		   //}
		
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder 
			auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
	}

