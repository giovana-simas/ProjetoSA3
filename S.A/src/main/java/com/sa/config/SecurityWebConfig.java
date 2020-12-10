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
	//autoriza a ultilização do repositorio do security
	@Autowired
	private SaDetailService saDetailService;
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http
			//".antMatchers("'pagina na qual vc quer permitir'")" verifica a pagina e a da um tipo de permissao
			//".permitAll()" da acesso para todas as permissoes
			//".hasRole("permissao criada anteriormente")" da permissao para aquela pagina apenas para quem tem a permissao posta
			//"/**" significa que todos os arquivos dentro desta pagina ou arquivo terão a mesma permissão que o mesmo ex: "/cadastro/**" ou seja todas as paginas dentro de cadastro
		    //Habilitar ou desabilitar paginas	
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/diretor/cadastroDiretor/**").permitAll()
			.antMatchers("/professor/cadastroProfessor/**").permitAll()
			.antMatchers("/aluno/cadastroAluno/**").permitAll()
			.antMatchers("/aluno/perfil/**").hasRole("padrao")
			.antMatchers("/diretor/perfil/**").hasRole("diretor")
			.antMatchers("/professor/perfil/**").hasRole("professor")
			.antMatchers("/professor/instituicao/**").hasRole("professor")
			.antMatchers("/diretor/instituicao/**").hasRole("diretor")
			.antMatchers("/aluno/instituicao/**").hasRole("padrao")
			.antMatchers("/instituicao/**").permitAll()
			.antMatchers("/diretor/addiInstituicao/**").hasRole("diretor")
			.antMatchers("/diretor/add/**").hasRole("diretor")
			.antMatchers("/aluno/insertinstituicao").hasRole("padrao")
			.antMatchers("/aluno/insertinstituicao").hasRole("padrao")
			.antMatchers("/professor/insertinstituicao").hasRole("professor")
			.antMatchers("/professor/insertinstituicao").hasRole("professor")
			.antMatchers("/diretor/addsala/**").hasRole("diretor")
			.antMatchers("/sala/save/**").hasRole("diretor")
			//Habilitar statics ou seja as bibliotecas
			.antMatchers("/bootstrap-4.5.2/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/fontawesome-5.14.0/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/src/**").permitAll()
			//Habilitar metodos
			.antMatchers("/aluno/save").permitAll()
			.antMatchers("/diretor/save").permitAll()
			.antMatchers("/professor/save").permitAll()
			.anyRequest().authenticated()
			.and()
			//definir pagina de login
			.formLogin()
				//url para pagina padrao de login
				.loginPage("/login")
				//caso o login seja efetuado com sucesso
				.defaultSuccessUrl("/instituicao", true)
				.permitAll()
			.and()
			//Relembrar usuario logado
			.rememberMe();
	}
	
	//cria o encripitador de senhas
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception{
		builder
			.userDetailsService(saDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());
		
	
	}
	
	
	
}
