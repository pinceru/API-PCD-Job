package com.pcdjob.config.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable().cacheControl();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/*").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/*").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/*").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/*").permitAll();
        http.headers().frameOptions().sameOrigin();
        http.cors().configure(http);
        
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}

}
