package com.uni_course_planner.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.service.entity.user.CustomUserDetailsService;

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
					"/" + PageRoutes.LOGIN,
					"/" + PageRoutes.FORGOT_PASSWORD,
					"/" + PageRoutes.VERIFICATION_CODE,
					"/" + PageRoutes.REGISTER,
                    "/css/**",
                    "/js/**").permitAll()
			//All other requests require an authenticated user
			.anyRequest().authenticated()
		)
		//Configure the custom logIn-form
		.formLogin(form -> form
			.loginPage("/" + PageRoutes.LOGIN)
			.loginProcessingUrl("/login")
			.usernameParameter("user-field")
		    .passwordParameter("password-field")
		    
		    //Redirect to the home-page after a successful logIn
			.defaultSuccessUrl("/" + PageRoutes.HOME, true)
			
			//Redirect back to the login page after a failed login attempt
			.failureUrl("/" + PageRoutes.LOGIN + "?error=true")
			.permitAll()
		)
		//Keep users signed in for up to seven days
		.rememberMe(r -> r
			.key("my-secret-key")
			.tokenValiditySeconds(604800)
			.userDetailsService(userDetailsService)
			.rememberMeParameter("remember-me")
		)
		//Configure the logout process
		.logout(logout -> logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/" + PageRoutes.LOGIN)
			.permitAll()
		)
		
		//Register the user service used for authentication
		.userDetailsService(userDetailsService);
		
		return http.build();
    }
	
	//Configure BCrypt for password hashing
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
