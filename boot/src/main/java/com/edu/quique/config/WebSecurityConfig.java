package com.edu.quique.config;

import org.springframework.beans.factory.annotation.Value;
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
  @Value("${config.ldap.user-search-filter}")
  private String USER_SEARCH_FILTER;
  @Value("${config.ldap.search-base}")
  private String SEARCH_BASE;
  @Value("${config.ldap.group-search-filter}")
  private String GROUP_SEARCH_FILTER;
  @Value("${config.ldap.url}")
  private String URL;
  @Value("${config.ldap.admin-user}")
  private String USER;
  @Value("${config.ldap.admin-pass}")
  private String PASS;

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
        .userSearchFilter(USER_SEARCH_FILTER)
        .userSearchBase(SEARCH_BASE)
        .groupSearchFilter(GROUP_SEARCH_FILTER)
        .groupSearchBase(SEARCH_BASE)
        .contextSource()
        .url(URL)
        .managerDn(USER)
        .managerPassword(PASS);
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
