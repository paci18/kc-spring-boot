package com.tesi.kcspringboot.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@KeycloakConfiguration
/*
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Import({KeycloakSpringBootConfigResolver.class})
*/
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class SecurityConfig   extends  KeycloakWebSecurityConfigurerAdapter{
    @Bean
    public Keycloak keycloak(KeycloakSpringBootProperties props) {
        return KeycloakBuilder.builder()
                .serverUrl(props.getAuthServerUrl())
                .realm(props.getRealm()) //
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(props.getResource())
                .clientSecret((String) props.getCredentials().get("secret"))
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            //     http.antMatcher("/user").authorizeRequests().anyRequest().hasAnyRole("administrator");
//        http.authorizeRequests().antMatchers("/user*")
////                .hasAuthority("administrator").anyRequest().authenticated();
           /* http.authorizeRequests().antMatchers("/user*").hasRole("[administrator]").anyRequest().authenticated();
            http.csrf().disable();
        */
            http.authorizeRequests().antMatchers("/user/*").hasRole("administrator").anyRequest().permitAll();

    }







    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
       keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }



//    public KeycloakSecurityContext getKeycloakSecurityContext(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        KeycloakAuthenticationToken token;
//        KeycloakSecurityContext context;
//        if (authentication == null){
//            throw new IllegalStateException("Cannot set authorization  beacuse there is not authenticated principal");
//        }
//        if (! KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
//            throw new IllegalStateException(String.format("Cannot set authorization header because Authentication is of type %s but %s is required",
//                    authentication.getClass(), KeycloakAuthenticationToken.class));
//        }
//        token= (KeycloakAuthenticationToken) authentication;
//        context = token.getAccount().getKeycloakSecurityContext();
//        return context;
//    }
}