package com.way2learnonline;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@Order(2)
// TODO-2 Make the below class to extend WebSecurityConfigurerAdapter
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter 
{
	
	// TODO-3 Uncomment the below to add httpBasic authentication for your app
	
	
	protected void configure(HttpSecurity http) throws Exception {		
		
	/*	  http .authorizeRequests() 
		  		.anyRequest().authenticated() 
		  		.and() .httpBasic();
		*/
		http
		.authorizeRequests()
		.antMatchers("/h2-console/**","/mylogin").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/mylogin").defaultSuccessUrl("/hello",true).
			and().csrf().disable().rememberMe().key("myKey");
			
	

	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/webjars/**");
	}


	
	
	
	//TODO-11 uncomment the below to configure jdbc authentication using DelegatingPasswordEncoder
	
	
	  
	  
	 @Autowired
	private DataSource dataSource;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource).passwordEncoder(passwordEncoder);
	}	
	
	
	
	
}
