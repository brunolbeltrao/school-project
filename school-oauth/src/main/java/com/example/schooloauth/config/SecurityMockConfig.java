package com.example.schooloauth.config;

import br.com.petrobras.authorization.provider.CompetitividadeAuthenticationProvider;
import br.com.petrobras.authorization.service.CAMockService;
import br.com.petrobras.security.DefaultSecurityContext;
import br.com.petrobras.security.ISecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("develop")
public class SecurityMockConfig  {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration-miliseg}")
	private String jwtExpirationMiliseg;

	@Autowired
	private CompetitividadeAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	protected ISecurityContext iSecurityContext(){
		return new DefaultSecurityContext();
	}

	@Bean
	protected UserDetailsService userDetailsService(){
		return new CAMockService(passwordEncoder());
	}
}
