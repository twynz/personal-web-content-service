package com.myweb.content.config.oauth2;

import com.myweb.content.filter.SecurityContextFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) //should add this annotation, for letting @preAuthorize works.
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenCheckService tokenCheckService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests().antMatchers("/content/health","/api/article/list/**","/api/article/single/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(securityContextFilter(), AnonymousAuthenticationFilter.class);
    }


    //    @Bean
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenServices(tokenCheckService);
//    }
//    @LoadBalanced
//    @Bean
//    public RestTemplate loadbalancedRestTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    public SecurityContextFilter securityContextFilter() {
        return new SecurityContextFilter();
    }

}
