package pl.noteally.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.noteally.services.UserService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurity {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/css/**", "/images/**","/js/**").permitAll()
                        .requestMatchers("/registerMe","/**","/login").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
