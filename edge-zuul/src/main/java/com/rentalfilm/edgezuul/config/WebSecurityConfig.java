package com.rentalfilm.edgezuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.social.security.SpringSocialConfigurer;




//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// This bean is load the user specific data when form login is used.
	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers(// Pages do not require login
					"/", 
					"/home",
	                "/*.html",
	                "/**/favicon.ico",
	                "/**/*.html",
	                "/**",
	                "/**/*.css",
	                "/**/*.js"
					).permitAll()
		 	.antMatchers(
		 			"/public/**",
		 			"errors/**"
		 			).permitAll();
		
		http.authorizeRequests()
			// All the Url require an authentication of the user
	        .antMatchers("/private/admin/**").hasAnyAuthority("ROLE_ADMIN")
	        .antMatchers("/private/staff/**").hasAnyAuthority("ROLE_STAFF")
	        .antMatchers("/private/customer/**").hasAuthority("ROLE_CUSTOMER")
//	        .antMatchers("/private/admin/**").access("hasRole('" + RoleEmunType.ROLE_ADMIN + "')")
//	        .antMatchers("/private/staff/**").access("hasRole('" + RoleEmunType.ROLE_STAFF + "')")
//	        .antMatchers("/private/customer/**").access("hasRole('" + RoleEmunType.ROLE_CUSTOMER + "')")
	        .anyRequest().authenticated()
	        
	        .and()
	        .formLogin()
			.loginProcessingUrl("/public/login") // Submit URL 
			.loginPage("/public/login")
			.successHandler(successHandler)// On login success we open the custom Dashboard for any USER ROLE
			.failureUrl("/public/login?error=true")// If the login process return error
			.usernameParameter("username")
			.passwordParameter("password")
	        
	        .and()
	        .logout()
	        .invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/public/logout"))
			.logoutUrl("/public/logout")
			.logoutSuccessUrl("/")
		
	        .and()
	        .exceptionHandling().accessDeniedPage("/errors/403");
		
		// Spring Social Config.
		//http.apply(new SpringSocialConfigurer()).signupUrl("/public/signup");
	}

}
