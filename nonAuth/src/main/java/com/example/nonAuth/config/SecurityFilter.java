package com.example.nonAuth.config;




import com.example.nonAuth.user.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {


    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig -> {
                    authConfig.requestMatchers( "/auth/register").permitAll();
                    authConfig.requestMatchers( "/auth/authenticate").permitAll();
                    authConfig.requestMatchers("/error").permitAll();
                    authConfig.requestMatchers( "/veterinaire/**").hasAuthority(Permission.ADMIN.name());
                    authConfig.requestMatchers( "/vaccination/**").hasAuthority(Permission.ADMIN.name());
                    authConfig.requestMatchers( "/dogs/**").hasAuthority(Permission.ADMIN_USER_ROLE.name());
                    authConfig.requestMatchers( "/proprietaire/**").hasAuthority(Permission.ADMIN_USER_ROLE.name());
                    authConfig.requestMatchers( "/Mail/**").hasAuthority(Permission.ADMIN.name());

                    authConfig.anyRequest().denyAll();


                });

        return http.build();

    }
}