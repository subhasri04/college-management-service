package in.gopal.college.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.gopal.college.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	
//	User Login → Generate Token → 
//	Client sends Token in Header → 
//	Filter validates token → 
//	Access Granted
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf-> csrf.disable())
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/swagger-ui/**","/api/auth/**","/v3/api-docs/**","/api/**").permitAll()
			.anyRequest().authenticated()
			)
			.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
	
		return http.build();
	}
}
