package ControleGastos.ControleGastos.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private String jwtIssuer;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      http.authorizeHttpRequests((auth) -> auth
//        .requestMatchers("/**").permitAll()
//        .requestMatchers("/actuator/**").permitAll()
//        .anyRequest().authenticated()
//      ).oauth2Client(Customizer.withDefaults())
//        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//      return http.build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        this.jwtIssuer = System.getProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri");
//        return JwtDecoders.fromIssuerLocation(jwtIssuer);
//    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
