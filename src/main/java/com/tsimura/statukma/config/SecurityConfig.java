package com.tsimura.statukma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> antMatchers = new ArrayList<>();

        antMatchers.addAll(Arrays.asList("/css/**", "/js/**", "/images/**", "/message/**",
                "/pages/**", "/contactUs", "/public/**"));

        if (Arrays.asList(environment.getActiveProfiles()).contains("unprotected")) {
            antMatchers.add("/api/**");
        }

        http
//                .cors()
//                .and()
                .authorizeRequests()
                .antMatchers(antMatchers.toArray(new String[antMatchers.size()]))
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().logout()
                .permitAll();
        http.csrf().disable();
    }


//    /**
//     * URLs from api that we use for display results of qindi and internal generated links.
//     * */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/qindi/**",
//                "/api/groups/{\\d+}/leaderBoard",
//                "/api/groups/{\\d+}/name");
//    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "HEAD", "PATCH"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList(
//                "Access-Control-Allow-Headers",
//                "Origin,Accept",
//                "X-Requested-With",
//                "Content-Type",
//                "Access-Control-Request-Method",
//                "Access-Control-Request-Headers"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
