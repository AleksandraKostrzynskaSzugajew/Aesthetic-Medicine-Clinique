package pl.alekosszu.KME.security;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SesSecurityConfig {

    private final BCryptPasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()

        );
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**", "/emp/**").hasRole("ADMIN")
                        .requestMatchers("/", "/home", "/register", "/logout").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .permitAll()
                                .defaultSuccessUrl("/someuser/loggedin") // Domyślna strona dla użytkownika

                )
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login")
                        .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll())

                        //dodane na potrzeby OAuth2
                .oauth2Login((oauth2) -> oauth2
                        .defaultSuccessUrl("/oauth2-success")
                        .loginPage("/login"));

        return http.build();

    }


}