package net.xusage.springboot.registration.config;

import net.xusage.springboot.registration.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/public/**")
                        .ignoringRequestMatchers("/assets/**")
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                )
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/public/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user/dashboard").failureUrl("/public/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/public/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/public/login")  // 当访问被拒绝时跳转到登录页面
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
