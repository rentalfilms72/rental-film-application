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




@Configuration
@EnableWebSecurity
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
	                //"/**",
	                "/**/*.css",
	                "/**/*.js"
					).permitAll()
		 	.antMatchers(
		 			"/msa-actor/actor/public/**",
		 			"/msa-admin/admin/public/**",
		 			"/msa-authority/authority/public/**",
		 			"/msa-category/category/public/**",
		 			"/msa-country/country/public/**",
		 			"/msa-clientui/clientui/public/**",
		 			"/msa-customer/customer/public/**",
		 			"/msa-film/film/public/**",
		 			"/msa-filmactor/filmactor/public/**",
		 			"/msa-filmcategory/filmcategory/public/**",
		 			"/msa-filmfeatures/filmfeatures/public/**",
		 			"/msa-filmrating/filmrating/public/**",
		 			"/msa-inventory/inventory/public/**",
		 			"/msa-language/language/public/**",
		 			"/msa-payment/payment/public/**",
		 			"/msa-picture/picture/public/**",
		 			"/msa-pwdresettoken/pwdresettoken/public/**",
		 			"/msa-rental/rental/public/**",
		 			"/msa-specialfeatures/specialfeatures/public/**",
		 			"/msa-staff/staff/public/**",
		 			"/msa-store/store/public/**",
		 			"/msa-user/user/public/**",
		 			"/msa-userauthority/userauthority/public/**",
		 			"errors/**"
		 			).permitAll();
		
//		http.authorizeRequests()
//			.antMatchers("/public/**",
//						"/clientui/**",
//						"/test-customer"
//						//"/**"
//						)
//			.permitAll();
		
		http.authorizeRequests()
			// All the Url require an authentication of the user
	        .antMatchers("/admin/private/**").hasAnyAuthority("ROLE_ADMIN")
	        .antMatchers("/staff/private/**").hasAnyAuthority("ROLE_STAFF")
	        .antMatchers("/customer/private/**").hasAuthority("ROLE_CUSTOMER")

	        .anyRequest().authenticated()
	        
	        .and()
	        .formLogin()
			.loginProcessingUrl("/clientui/public/login") // Submit URL 
			.loginPage("/clientui/public/login")
			.successHandler(successHandler)// On login success we open the custom Dashboard for any USER ROLE
			.failureUrl("/clientui/public/login?error=true")// If the login process return error
			.usernameParameter("username")
			.passwordParameter("password")
	        
	        .and()
	        .logout()
	        .invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/clientui/public/logout"))
			.logoutUrl("/clientui/public/logout")
			.logoutSuccessUrl("/")
		
	        .and()
	        .exceptionHandling().accessDeniedPage("/errors/403");
		
		// Spring Social Config.
		//http.apply(new SpringSocialConfigurer()).signupUrl("/public/signup");
	}

}
