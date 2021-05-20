package com.sa.config;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sa.security.SaDetailService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
				.antMatchers("/aluno/perfil/**").hasAnyRole("professor","diretor","aluno")
				.antMatchers("/diretor/perfil/**").hasAnyRole("professor","diretor","aluno")
				.antMatchers("/professor/perfil/**").hasAnyRole("professor","diretor","aluno")
				.antMatchers("/perfil/**").hasAnyRole("professor","diretor","aluno")
				//permissoes chat
			.antMatchers("/chat/**").hasAnyRole("professor","diretor","aluno")
				//permissoes feed
			.antMatchers("/feed/**").hasAnyRole("professor","diretor","aluno")

				//permissoes instituicao
			.antMatchers("/instituicao/listInstituicoes/**").hasAnyRole("professor","diretor","aluno")
			.antMatchers("/instituicao/addinstituicao/**").hasAnyRole("diretor", "aluno", "professor")
			.antMatchers("/aluno/insertinstituicao").hasRole("aluno")
			.antMatchers("/professor/insertinstituicao").hasRole("professor")
				//permissoes sala
			.antMatchers("/sala/addsala/**").hasAnyRole("diretor","aluno","professor")
			.antMatchers("/sala/criaSala/**").hasAnyRole("professor","diretor")
			.antMatchers("/sala/save/**").hasAnyRole("diretor","professor")
			.antMatchers("/sala/insertSala/**").hasAnyRole("professor","aluno")
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
				.antMatchers("/materia/*").permitAll()


				.anyRequest().authenticated()
			.and()
			//definir pagina de login
			.formLogin()
				//url para pagina padrao de login
				.loginPage("/login")
				//caso o login seja efetuado com sucesso
				.defaultSuccessUrl("/instituicao/listInstituicoes/", true)
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
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
