package com.example.DentalEsquivel;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private DataSource datasource;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/images/**","/css/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login")
            .permitAll()
            .and()
            .logout().permitAll();
    }
    
    
    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.jdbcAuthentication()
                .dataSource(datasource)
                .passwordEncoder(passEncoder)
                .usersByUsernameQuery("select username, password, activo from usuarios where username=?")
                .authoritiesByUsernameQuery("select u.username, r.rol from roles r inner join usuarios u on u.idrol = r.id_rol where u.username=?");
    }
    
}
