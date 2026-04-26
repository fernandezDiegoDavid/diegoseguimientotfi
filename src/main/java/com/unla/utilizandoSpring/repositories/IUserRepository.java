package com.unla.utilizandoSpring.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.utilizandoSpring.entities.User;

/**
 * Repositorio encargado de la persistencia de la entidad {@link User}.
 * <p>
 * Proporciona acceso a los datos de usuario y funcionalidades específicas para
 * el proceso de autenticación y autorización del sistema.
 * </p>
 */

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	/**
	 * Busca un usuario por su nombre de usuario (username) y recupera sus roles
	 * asociados en una única consulta de base de datos.
	 * <p>
	 * Se utiliza la cláusula {@code JOIN FETCH} para forzar una carga de tipo
	 * 'Eager' sobre la colección de roles. Esto es crítico para la integración con
	 * <b>Spring Security</b>, ya que evita excepciones de tipo
	 * {@code LazyInitializationException} al acceder a las autoridades del usuario
	 * fuera del contexto transaccional.
	 * </p>
	 * 
	 * @param username El nombre de usuario único a buscar.
	 * @return El objeto {@link User} con su colección de roles inicializada, o
	 *         {@code null} si el usuario no existe.
	 */
	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);
}
