package com.unla.utilizandoSpring.service.implemantation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.utilizandoSpring.entities.UserRole;
import com.unla.utilizandoSpring.repositories.IUserRepository;

@Service("userService")
// Marca esta clase como un @Service de Spring, es decir, un componente que puede ser inyectado
// "userService" es el nombre con el que será registrada. Puede ser usado en otras partes de código

public class UserService implements UserDetailsService {
	// Spring necesita una implementación de esta interfaz para cargar usuarios
	// desde la BD al hacer login

	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// Inyecta el repositorio para acceder a los datos del usuario en la base
	// IUserRepository debe tener el método FindByUsernameAndFetchUserRolesEagerly

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Este metodo es llamdo automáticamente por Spring Security cuando
		// alguien intenta loguearse
		
		com.unla.utilizandoSpring.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		// Busca el usuario con ese nombre
		// La parte "FetchUserRolesEargely" indica que también trae los roles de ese usuario
		// importante para la autorización
		
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
		// buildGrantedAuthorities(...) convierte los roles en objetos GrantedAuthority (forma que entiende Spring).
		// buildUser(...) crea un objeto User (de Spring Security), que implementa UserDetails.
	}

	private User buildUser(com.unla.utilizandoSpring.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		//Recorre los roles del usuario (UserRole).
		//Los convierte a GrantedAuthority, que es lo que Spring Security usa para verificar permisos.
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
}
