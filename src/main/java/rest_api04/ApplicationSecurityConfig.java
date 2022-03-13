package rest_api04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

	/*
	 	Admin ==> GET(Read), PUT + POST + PATCH + DELETE(Write)
	 	Student ==> GET(Read)
	 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)//If you want to enable @PreAuthorize in any class use this.
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().//When you put those, it means you unblocked PUT, POST, PATCH, DELETE methods
			authorizeRequests().
			antMatchers("/", "index", "/css/*", "js/*").permitAll().
			//antMatchers("/**").hasRole(ApplicationUserRoles.ADMIN.name()).//If you type that just ADMIN will be able to use all methods
			                                                              //If you do not type that all roles will be able to use all methods 
			anyRequest().
			authenticated().
			and().
			httpBasic();//Basic-Authentication: Uses username and password for every request
	}
	
	private final PasswordEncoder passwordEncoder;	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails student = User.
								builder().
								username("techproed").
								password(passwordEncoder.encode("password123")).
								//roles(ApplicationUserRoles.STUDENT.name()).
								authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities()).
								build(); 
		
		UserDetails admin = User.
								builder().
								username("admin").
								password(passwordEncoder.encode("nimda")).
								//roles(ApplicationUserRoles.ADMIN.name()).
								authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).
								build();
						
		return new InMemoryUserDetailsManager(student, admin);

	}

}
