package com.bmeonlab.valki.webshop.config;

import com.bmeonlab.valki.webshop.security.JwtAuthenticationFilter;
import com.bmeonlab.valki.webshop.security.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(        // TODO: Do I need this?
        prePostEnabled = true
)
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username,password,enabled from customer where username=?")    // setting the users table: 'customer'
                .authoritiesByUsernameQuery("SELECT customer.username as username, role.name as role FROM customer INNER JOIN user_roles ON customer.id=user_roles.customer_id INNER JOIN role ON user_roles.role_id=role.id WHERE customer.username=?");            // setting the authorities table: 'user_roles'
    }


    // Secure the endpoins with HTTP Basic authentication   TODO: fix, modify, complete, understand
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/authenticate").permitAll()
                .antMatchers(HttpMethod.GET).hasAuthority("USER")    // TODO: this helps now, but should be reviewed later
                .antMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/product").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers("**").denyAll()                            // Denies every other request
                .and()
                .csrf().disable()
                .formLogin().disable();

         */

        http.cors().and()
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/authenticate").permitAll()
                .antMatchers(HttpMethod.GET).hasAuthority("USER")    // TODO: this helps now, but should be reviewed later
                .antMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/product").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers("**").denyAll()                            // Denies every other request
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
