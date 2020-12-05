package com.rentalfilm.edgezuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.rentalfilm.edgezuul.security.JwtTokenProvider;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private JwtTokenProvider jwtTokenProvider;

//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	AuthenticationSuccessHandler successHandler;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	// This bean is load the user specific data when form login is used.
//	@Override
//	public UserDetailsService userDetailsService() {
//		return userDetailsService;
//	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow eureka client to be accessed without authentication
		web.ignoring().antMatchers("/*/")//
		.antMatchers("/eureka/**")//
		.antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
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
		 			//"/msa-clientui/clientui/public/**", /////
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
		
		http.authorizeRequests()
			// All the Url require an authentication of the user
	        .antMatchers("/admin/private/**").hasAuthority("ROLE_ADMIN")
	        .antMatchers("/staff/private/**").hasAuthority("ROLE_STAFF")
	        .antMatchers("/customer/private/**").hasAuthority("ROLE_CUSTOMER")
	        
	        .antMatchers(
	        		"/msa-actor/actor/private/**",
		 			"/msa-admin/admin/private/**",
		 			"/msa-authority/authority/private/**",
		 			"/msa-category/category/private/**",
		 			"/msa-country/country/private/**",
		 			//"/msa-clientui/clientui/private/**", /////
		 			"/msa-customer/customer/private/**",
		 			"/msa-film/film/private/**",
		 			"/msa-filmactor/filmactor/private/**",
		 			"/msa-filmcategory/filmcategory/private/**",
		 			"/msa-filmfeatures/filmfeatures/private/**",
		 			"/msa-filmrating/filmrating/private/**",
		 			"/msa-inventory/inventory/private/**",
		 			"/msa-language/language/private/**",
		 			"/msa-payment/payment/private/**",
		 			"/msa-picture/picture/private/**",
		 			"/msa-pwdresettoken/pwdresettoken/private/**",
		 			"/msa-rental/rental/private/**",
		 			"/msa-specialfeatures/specialfeatures/private/**",
		 			"/msa-staff/staff/private/**",
		 			"/msa-store/store/private/**",
		 			"/msa-user/user/private/**",
		 			"/msa-userauthority/userauthority/private/**"
	        		).hasAnyAuthority("ROLE_ADMIN", "ROLE_STAFF", "ROLE_CUSTOMER")

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
