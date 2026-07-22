package org.example.employeesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService users() {
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin123")
                        .roles("ADMIN")
                        .build();
        UserDetails manager =
                User.withDefaultPasswordEncoder()
                        .username("manager")
                        .password("manager123")
                        .roles("MANAGER")
                        .build();
        UserDetails employee =
                User.withDefaultPasswordEncoder()
                        .username("employee")
                        .password("employee123")
                        .roles("EMPLOYEE")
                        .build();
        return new InMemoryUserDetailsManager(admin, manager, employee);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/h2-console/**").permitAll()


                        .requestMatchers(HttpMethod.GET, "/employees/**")
                        .hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")

                        .requestMatchers(HttpMethod.POST, "/employees/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/employees/**")
                        .hasAnyRole("ADMIN", "MANAGER")

                        .requestMatchers(HttpMethod.DELETE, "/employees/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/employees", true)
                        .failureUrl("/login?error")
                        .permitAll()
                ).httpBasic(httpBasic -> {
                });
        ;
        return http.build();
    }

}
