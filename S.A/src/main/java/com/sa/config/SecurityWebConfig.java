package com.sa.config;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sa.security.SaDetailService;


@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private SaDetailService saDetailService;
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http
			//Habilitar ou desabilitar paginas
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/cadastro").permitAll()
			.antMatchers("/usuario/perfil").hasRole("padrao")
			//Habilitar statics
			.antMatchers("/bootstrap-4.5.2/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/fontawesome-5.14.0/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/src/**").permitAll()
			.anyRequest().authenticated()
			.and()
			//definir pagina de login
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/usuario/perfil", true)
				.permitAll()
			.and()
			//Relembrar usuario logado
			.rememberMe();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception{
		builder
			.userDetailsService(saDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());
		
	
	}
	
	public static void main (String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
	
	
}
