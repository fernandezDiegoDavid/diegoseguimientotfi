package com.unla.utilizandoSpring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.unla.utilizandoSpring.service.implemantation.UserService;



@Configuration
// Indica que esta clase define beans para el contexto de Spring
// es parte de la configuración de la aplicación

@EnableWebSecurity
//activa la configuración de seguridad web personalizada con Spring Security

@EnableMethodSecurity
// permite usar anotaciones como @PreAutorize o @Secured, sobre metodos
public class SecurityConfiguration {

	private final UserService userService;

	public SecurityConfiguration(UserService userService) {
		this.userService = userService;
	}
	// Spring inyecta el servicio personalizado que implementa UserDetailService
	// Este se usará para autenticar usuarios desde la base de datos u otra fuente

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		// Define la configuración principal de seguridad web
		
		return http
				.csrf(AbstractHttpConfigurer::disable)
				// Desactiva la proteccion CSRF
				.cors(AbstractHttpConfigurer::disable)
				// Desactiva restricciones de cors
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/css/*", "/imgs/*", "/js/*", "/vendor/bootstrap/css/*",
							"/vendor/jquery/*", "/vendor/bootstrap/js/*", "/api/v1/**").permitAll();
					// Permite acceso sin login a rutas estáticas como CSS,JS,etc
					auth.anyRequest().authenticated();
					// Cualquier otra ruta requiere que el usuario esté autenticado
				})
				.formLogin(login -> {
					login.loginPage("/login");						// La vista personalizada de login
					login.loginProcessingUrl("/loginprocess");		// Spring Security intercepta POST aqui
					login.usernameParameter("username");			// Nombre del input de usuario
					login.passwordParameter("password");			// Nombre del input de contraseña
					login.defaultSuccessUrl("/loginsuccess",true);	// Redireccion tras login exitoso
					login.permitAll();								// Permite acceder al login sin login
				})
				.logout(logout -> {
					logout.logoutUrl("/logout");					// Ruta para cerrar sesión
					logout.logoutSuccessUrl("/login");				// A dónde redirigir tras logout 
					logout.permitAll();								// Cualquiera puede acceder a /logout
				})
				.build(); // Compila toda la configuración en una SEcurityFilterChain, que es la forma moderna de configurar Spring Security
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	// Recupera el AuthenticationManager ya configurado por Spring, necesario si usas AutenticationProvider

	@Bean
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder()); // usa BCrypt
		provider.setUserDetailsService(userService);    // usa la implementación de UserDetailService
		return provider;
	}
	// Usa el servicio para cargar usuarios
	// Compara la contraseña ingresada con la almacenada ( con BCrypt )

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	// Define el codificador de contraseñas 
}

