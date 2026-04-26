package com.unla.utilizandoSpring.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Entidad que representa a un usuario dentro del sistema de seguridad. Almacena
 * las credenciales de acceso, el estado de la cuenta y sus auditorías.
 * 
 * @author Fernandez Diego
 * @version 1.0
 */

@Entity
public class User {
	/** Identificador único autoincremental en la base de datos. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Nombre de usuario único utilizado para el inicio de sesión. Máximo 45
	 * caracteres.
	 */
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;

	/** Contraseña encriptada (BCryp ) del usuario. */
	@Column(name = "password", nullable = false, length = 60)
	private String password;

	/** Indica si el usuario está aactivo o ha sido deshabilitado para el acceso. */
	private boolean enabled;

	/**
	 * Fecha y hora de creación del registro. Se genera automáticamente al insertar.
	 */
	@Column(name = "createdat", columnDefinition = "DATETIME")
	@CreationTimestamp
	private LocalDateTime createdAt;

	/**
	 * Fecha y hora de la última actualización. Se actualiza automáticamente en cada
	 * modificación.
	 */
	@Column(name = "updatedat", columnDefinition = "DATETIME")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	/**
	 * Colección de roles asociados al usuario. Se cargan de forma diferida (LAZY)
	 * para optimizar el rendimiento.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserRole> userRoles = new HashSet<>();

	// --- Constructores ---

	/**
	 * Crea un usuario con los datos básicos de acceso.
	 * 
	 * @param username El nombre de usuario.
	 * @param password La contraseña (debe venir ya encriptada).
	 * @param enabled  El estado inicial de la cuenta.
	 */
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * Constructor completo incluyendo la asignación inicial de roles.
	 * 
	 * @param username  El nombre de usuario.
	 * @param password  La contraseña.
	 * @param enabled   El estado.
	 * @param userRoles Conjunto de roles iniciales.
	 */
	public User(String username, String password, boolean enabled, Set<UserRole> userRoles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRoles;
	}

	/** Constructor vacío requerido por JPA. */
	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}