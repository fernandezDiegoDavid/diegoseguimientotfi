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

/**
 * Implementación personalizada de {@link UserDetailsService} para integrar la
 * persistencia de usuarios con el motor de autenticación de Spring Security.
 * <p>
 * Esta clase se encarga de recuperar los datos del usuario desde el repositorio
 * y adaptarlos al formato requerido por el framework de seguridad.
 * </p>
 */
@Service("userService")
// Marca esta clase como un @Service de Spring, es decir, un componente que puede ser inyectado
// "userService" es el nombre con el que será registrada. Puede ser usado en otras partes de código

public class UserService implements UserDetailsService {
	// Spring necesita una implementación de esta interfaz para cargar usuarios
	// desde la BD al hacer login

	private IUserRepository userRepository;

	/**
	 * Constructor para la inyección de dependencias del repositorio de usuarios.
	 * 
	 * @param userRepository Repositorio que gestiona la entidad User.
	 */
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Método principal llamado por Spring Security durante el proceso de
	 * autenticación.
	 * <p>
	 * Recupera un usuario de la base de datos incluyendo sus roles de forma 'Eager'
	 * para garantizar que la autorización funcione correctamente.
	 * </p>
	 * 
	 * @param username Nombre del usuario que intenta iniciar sesión.
	 * @return Una instancia de {@link UserDetails} con la información del usuario.
	 * @throws UsernameNotFoundException Si el nombre de usuario no existe en la
	 *                                   base de datos.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.unla.utilizandoSpring.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);

		// Verificación de existencia para evitar NullPointerException
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
		}

		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
		// buildGrantedAuthorities(...) convierte los roles en objetos GrantedAuthority
		// (forma que entiende Spring).
		// buildUser(...) crea un objeto User (de Spring Security), que implementa
		// UserDetails.
	}

	private User buildUser(com.unla.utilizandoSpring.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, // accountNonExpired,
																									// credentialsNonExpired,
																									// accountNonLocked,
				grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		// Recorre los roles del usuario (UserRole).
		// Los convierte a GrantedAuthority, que es lo que Spring Security usa para
		// verificar permisos.
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (UserRole userRole : userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
}
