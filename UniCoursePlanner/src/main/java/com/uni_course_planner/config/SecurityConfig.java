package com.uni_course_planner.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uni_course_planner.constants.views.PageAddress;
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
			//Öffentliche Seiten
			auth -> auth.requestMatchers(
					"/",
					"/register",
					"/login",
					"/changePasswort",
					"/verifyCode",
					"/" + PageAddress.PASSWORT_VERGESSEN_PAGE_ADDRESS,
					"/" + PageAddress.CODE_PAGE_ADDRESS,
					"/" + PageAddress.REGISTER_PAGE_ADDRESS,
                    "/css/**",
                    "/js/**").permitAll().anyRequest().authenticated()
		)
		//Eigenes LogIn
		.formLogin(form -> form
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.usernameParameter("user-field")
		    .passwordParameter("password-field")
			.defaultSuccessUrl("/" + PageAddress.HOME_PAGE_ADDRESS, true)
			.failureUrl("/login?error=true")
			.permitAll()
		)
		//Remember me für 7 Tage
		.rememberMe(r -> r
			.key("my-secret-key")
			.tokenValiditySeconds(604800)
			.userDetailsService(userDetailsService)
			.rememberMeParameter("remember-me")
		)
		//LogOut
		.logout(logout -> logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.permitAll()
		)
		//User-Service registrieren
		.userDetailsService(userDetailsService);
		
		
		return http.build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
