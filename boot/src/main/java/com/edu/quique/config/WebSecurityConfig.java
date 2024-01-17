package com.edu.quique.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/login/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.ldapAuthentication()
        .userSearchFilter("(mail={0})")
        .userSearchBase("ou=Users,dc=ieslavereda,dc=local")
        .groupSearchFilter("cn={0}")
        .groupSearchBase("ou=Users,dc=ieslavereda,dc=local")
        .contextSource()
        .url("ldap://localhost:389")
        .managerDn("cn=admin,dc=ieslavereda,dc=local")
        .managerPassword("123456789");
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  AuditorAware<String> dbAuditorAwareProvider() {
    return new AuditorAwareImpl();
  }

  @Bean
  public DateTimeProvider dbAuditorDateTimeProvider() {
    return () -> {
      return Optional.of(ZonedDateTime.now());
    };
  }
}
