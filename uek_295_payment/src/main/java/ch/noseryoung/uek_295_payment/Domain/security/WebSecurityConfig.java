  package ch.noseryoung.uek_295_payment.Domain.security;

  import lombok.RequiredArgsConstructor;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.http.HttpMethod;
  import org.springframework.security.authentication.AuthenticationManager;
  import org.springframework.security.authentication.ProviderManager;
  import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
  import org.springframework.security.config.Customizer;
  import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
  import org.springframework.security.config.http.SessionCreationPolicy;
  import org.springframework.security.core.userdetails.UserDetailsService;
  import org.springframework.security.crypto.password.PasswordEncoder;
  import org.springframework.security.web.SecurityFilterChain;

  @Configuration
  @RequiredArgsConstructor
  @EnableMethodSecurity
  public class WebSecurityConfig {

      @Autowired
      private UserDetailsService userService;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Bean
      public AuthenticationManager authenticationManager() {
          DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
          provider.setPasswordEncoder(passwordEncoder);
          provider.setUserDetailsService(userService);
          return new ProviderManager(provider);
      }

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          return http.authorizeHttpRequests(
                  requests -> requests
                          .requestMatchers(HttpMethod.GET, "/v3/api-docs", "/v3/api-docs/swagger-config", "/swagger-ui/*")
                          .permitAll()
                          .anyRequest().authenticated())
                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .cors(AbstractHttpConfigurer::disable)
                  .csrf(AbstractHttpConfigurer::disable)
                  .httpBasic(Customizer.withDefaults())
                  .build();
      }
  }
