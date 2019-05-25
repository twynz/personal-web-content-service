package com.myweb.content.config;

import com.myweb.content.config.oauth2.TokenCheckService;
import com.myweb.content.properties.TokenCheckServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Bean
    @ConfigurationProperties(prefix = "token")
    public TokenCheckServiceProperties tokenCheckServiceProperties() {
        return new TokenCheckServiceProperties();
    }

    @Bean
    public TokenCheckService getTokenCheckService(TokenCheckServiceProperties tokenCheckServiceProperties) {
        TokenCheckService tokenCheckService = new TokenCheckService();
        tokenCheckService.setCheckTokenEndpointUrl(tokenCheckServiceProperties.getCheckTokenEndpointUrl());
        tokenCheckService.setClientId(tokenCheckServiceProperties.getClientId());
        tokenCheckService.setClientSecret(tokenCheckServiceProperties.getClientSecret());
        tokenCheckService.setLoadBalancerClient(loadBalancerClient);
        return tokenCheckService;
    }
}
