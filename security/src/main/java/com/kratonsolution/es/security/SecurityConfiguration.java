/**
 * 
 */
package com.kratonsolution.es.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,proxyTargetClass=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationService authenticationService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .antMatchers("/backoffice/**").authenticated()
            .anyRequest().permitAll();

        http.logout();
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        auth.authenticationProvider(authProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new PasswordEncoderImpl());
        provider.setUserDetailsService(authenticationService);
        
        return provider;
    }
}
