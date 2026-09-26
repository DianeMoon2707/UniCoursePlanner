package com.uni_course_planner.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.service.relation.user.CustomUserDetailsService;

@Configuration
public class SecurityConfig 
{
	private final CustomUserDetailsService userDetailsService;
	
	public SecurityConfig(CustomUserDetailsService userDetailsService) 
	{
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		http.authorizeHttpRequests(
			//Public pages and resources that do not require authentication
			auth -> auth.requestMatchers(
					"/",
					"/register",
					"/login",
					"/changePassword",
					"/verifyCode",
					"/" + PageRoutes.FORGOT_PASSWORD,
					"/" + PageRoutes.VERIFICATION_CODE,
					"/" + PageRoutes.REGISTER,
                    "/css/**",
                    "/js/**").permitAll()
			//Alle anderen Seiten erfordern eine erfolgreiche Authentifizierung
			.anyRequest().authenticated()
		)
		//Konfiguration des benutzerdefinierten LogIn-Formulars
		.formLogin(form -> form
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.usernameParameter("user-field")
		    .passwordParameter("password-field")
		    
		    //Nach erfolgreicher Anmeldung zur Startseite weiterleiten
			.defaultSuccessUrl("/" + PageRoutes.HOME, true)
			
			//Bei fehlgeschlagener Anmeldung zur LogIn-Seite zurückkehren
			.failureUrl("/login?error=true")
			.permitAll()
		)
		//Angemeldeten Benutzern ermöglichen, für sieben Tage angemeldet zu bleiben
		.rememberMe(r -> r
			.key("my-secret-key")
			.tokenValiditySeconds(604800)
			.userDetailsService(userDetailsService)
			.rememberMeParameter("remember-me")
		)
		//Konfiguration des Logout-Vorgangs
		.logout(logout -> logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.permitAll()
		)
		
		//User-Service für die Authentifizierung registrieren
		.userDetailsService(userDetailsService);
		
		return http.build();
    }
	
	//Passwörter für die Datenbank mit BCrypt hashen
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
