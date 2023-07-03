package pl.alekosszu.KME.security;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SesSecurityConfig {


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/all", "/register").permitAll()
//                        .requestMatchers("/admin").hasRole("admin")
//                        .requestMatchers("/emp").hasRole("employee")
//                        .requestMatchers("/user").hasRole("user")
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                        .defaultSuccessUrl("/user/loggedin", true)
//                        .successHandler((request, response, authentication) -> {
//                            // Pobierz rolę z uwierzytelnienia
//                            String role = authentication.getAuthorities().iterator().next().getAuthority();
//
//                            System.out.println(role);
//
//                            // Sprawdź rolę i ustaw odpowiednią domyślną stronę
//                            if (role.equals("admin")) {
//                                response.sendRedirect("/admin/default");
//                            } else if (role.equals("user")) {
//                                response.sendRedirect("/user/home");
//                            } else if (role.equals("employee")) {
//                                response.sendRedirect("/employee/default");
//                            } else {
//                                // Domyślna strona dla innych ról
//                                response.sendRedirect("/default");
//                            }
//                        })
//                )
//                .logout((logout) -> logout.logoutUrl("/forall/logout").permitAll());
//
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                // ... the rest of your authorization rules
        );
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/all", "/register").permitAll()
                        .requestMatchers("/admin").hasRole("admin")
                        .requestMatchers("/emp").hasRole("employee")
                        .requestMatchers("/user").hasRole("user")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/user/loggedin")

                )
                .logout((logout) -> logout.logoutUrl("/forall/logout").permitAll());

        return http.build();
    }


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }
}