package com.edu.quique;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final static String USER_SEARCH_FILTER = "config.ldap.userFilter";
	private final static String USER_SEARCH_BASE = "dc=ieslavereda,dc=local";
	private final static String GROUP_SEARCH_BASE = "ou=Users,dc=ieslavereda,dc=local";
	private final static String GROUP_SEARCH_FILTER = "cn={0}";
	private final static String URL = "ldap://192.168.1.143";
	private final static int PORT = 389;
	private final static String USER =  "cn=admin,dc=ieslavereda,dc=local";
	private final static String PASSWORD = "12345678";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/login/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.ldapAuthentication()
//        .userSearchFilter("(uid={0})")
//        .userSearchBase("dc=ieslavereda,dc=local")
//        .groupSearchBase("ou=Users,dc=ieslavereda,dc=local")
//        .groupSearchFilter("cn={0}")
//        .contextSource()
//        .url("ldap://192.168.1.143")
//        .port(389)
//        .managerDn("cn=admin,dc=ieslavereda,dc=local")
//        .managerPassword("12345678");
		auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
    }

	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider =
				new ActiveDirectoryLdapAuthenticationProvider("ieslavereda.local", "ldap://localhost:389");
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		return provider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception  {
		return super.authenticationManagerBean();
	}

}
