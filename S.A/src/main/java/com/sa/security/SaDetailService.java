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

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email)
		throws UsernameNotFoundException{
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		if(usuario ==  null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return new UsuarioSistema(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), authorities(usuario));
	}
	
	public Collection<? extends GrantedAuthority> authorities(Usuario usuario){
		Collection<GrantedAuthority> autorizacoes = new ArrayList<>();
		List<Permissao> permissoes = permissaoRepository.findByUsuariosIn(usuario);
		for(Permissao permissao: permissoes) {
			autorizacoes.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
		}
		return autorizacoes;
	}
	
	
	
	
	
}
