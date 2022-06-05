package com.bicycleManagement.SecurityConfiguration;

import com.bicycleManagement.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSuccessHandler successHandler;

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().and().csrf().disable()
                .authorizeRequests()
//                .antMatchers("/admins").authenticated()
//                .antMatchers("/employee").hasRole()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/employee/**").hasRole("EMPLOYEE")
                .anyRequest().permitAll()
                .and()
                //  .formLogin()
                .formLogin().successHandler(this.successHandler)
                .usernameParameter("")
                .defaultSuccessUrl("/users")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/login")
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }

    @Component
    class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        @Override
        protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException {
            String targetUrl = determineTargetUrl(authentication);
            if (response.isCommitted()) {
                return;
            }
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }

        /**
         * Determines where the user should be redirected after a login considering the role.
         * ADMIN is redirected to /admin/running-rentals, which is a REST endpoint.
         * EMPLOYEE is redirected to the homepage /.
         * Everyone else is redirected to the login page.
         *
         * @param authentication authentication object used for getting the authorities of a request
         * @return the redirection URL as String
         */
        protected String determineTargetUrl(Authentication authentication) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<>();

            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }

            String url = "/login";

            if (roles.contains("ROLE_ADMIN")) {
                url = "/admin/running-rentals";
            }
            if (roles.contains("ROLE_EMPLOYEE")) {
                url = "/";
            }

            return url;
        }
    }
}
