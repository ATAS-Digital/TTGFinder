package ru.atas.TRPfinder.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService getUsers(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(System.getenv("ADMIN_PASSWORD"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

//    @Bean
//    public RoleHierarchyImpl roleHierarchy() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
//        return roleHierarchy;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/games/**").hasRole("ADMIN")
                .requestMatchers("/registrations/**").hasRole("ADMIN")
                .requestMatchers("/players/**").hasRole("ADMIN")
                //.requestMatchers("/test/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .disable();
        return http.build();
    }
}
