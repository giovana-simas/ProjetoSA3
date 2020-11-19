package com.sa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sa.model.Permissao;
import com.sa.model.Usuario;
import com.sa.repository.PermissaoRepository;
import com.sa.repository.UsuarioRepository;

@Component
public class SaDetailService implements UserDetailsService{

	//autoriza a ultilização do repositorio nesta classe
	@Autowired
	private UsuarioRepository usuarioRepository;
	//autoriza a ultilização do repositorio nesta classe
	@Autowired
	private PermissaoRepository permissaoRepository;
	//eu não entendo ao certo tudo nos codigos do security entao vou tentar explicar mais ou menos
	//cria um metodo do tipo detalhe de usuarios com o nome loadUserByUsername e uma variavel email que sera usada para validar o login
	@Override
	public UserDetails loadUserByUsername(String email)
		//caso o nome de usuario/email nao seja encontrado
		throws UsernameNotFoundException{
		//cria um objeto usuario e aplica as informações de um usuario existente atravez da procura do seu email
		Usuario usuario = usuarioRepository.findByEmail(email);
		//verifica se o usuario esta vazio
		if(usuario ==  null) {
			//caso esteja vazio mostrar uma ma mensagem de usuario nao encontrado(não é exatamente isso)
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		//retorna um novo usuario para o UsuarioSistema onde sera gravado em User
		return new UsuarioSistema(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), authorities(usuario));
	}
	
	
	//tudo isso é a verificação de permissoes do security que eu nao sei explicar...
	public Collection<? extends GrantedAuthority> authorities(Usuario usuario){
		
		Collection<GrantedAuthority> autorizacoes = new ArrayList<>();
		
		List<Permissao> permissoes = permissaoRepository.findByUsuariosIn(usuario);
		
		for(Permissao permissao: permissoes) {
			
			autorizacoes.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
		}
		return autorizacoes;
	}
	
	
	
	
	
}
