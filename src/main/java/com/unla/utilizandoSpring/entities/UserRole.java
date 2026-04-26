package com.unla.utilizandoSpring.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Entidad que representa los roles o permisos asignados a un {@link User}.
 * 
 * Esta clase define la autoridad que posee un usuario dentro del sistema (ej:
 * ROLE_ADMIN, ROLE_USER). La combinación de 'role' y 'user_id' es única para
 * evitar duplicidad de permisos en un mismo usuario
 */
@Entity
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "user_id" }))
public class UserRole {

	/** Identificador único de la asignación del rol. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Usuario al que pertenece este rol. Se utiliza carga perezosa (LAZY) para no
	 * sobrecargar la base de datos al consultar roles de forma independiente.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/**
	 * Nombre del rol o permiso (ej: "ROLE_ADMIN"). Longitud máxima de 100
	 * caracteres.
	 */
	@Column(name = "role", nullable = false, length = 100)
	private String role;

	/** Auditoría: Fecha y hora en la que se asignó el rol. */
	@Column(name = "createdat", columnDefinition = "DATETIME")
	@CreationTimestamp
	private LocalDateTime createdAt;

	/** Auditoría: Fecha y hora de la última modificación de la asignación. */
	@Column(name = "updatedat", columnDefinition = "DATETIME")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	// --- Constructores ---

	/**
	 * Constructor para inicializar un rol con datos existentes.
	 * 
	 * @param id   Identificador del registro.
	 * @param user Instancia del usuario asociado.
	 * @param role Nombre del permiso asignado.
	 */
	public UserRole(int id, User user, String role) {
		this.id = id;
		this.user = user;
		this.role = role;
	}

	/** Constructor vacío requerido por la especificación JPA. */
	public UserRole() {
	}

	// --- Getters y Setters ---

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

}
