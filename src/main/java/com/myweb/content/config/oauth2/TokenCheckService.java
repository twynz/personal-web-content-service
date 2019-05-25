package com.myweb.content.config.oauth2;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class TokenCheckService {
//http://localhost:8500/v1/health/checks/auth



    private final static String AUTH_SERVICE = "auth";

    private LoadBalancerClient loadBalancerClient;

    private RestOperations restTemplate;

    private String checkTokenEndpointUrl;

    private String clientId;

    private String clientSecret;

    private String tokenName = "token";

    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    public TokenCheckService() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(10000);
        requestFactory.setConnectTimeout(10000);
        restTemplate = new RestTemplate(requestFactory);
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
        this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenConverter(AccessTokenConverter accessTokenConverter) {
        this.tokenConverter = accessTokenConverter;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add(tokenName, accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getAuthorizationHeader(clientId, clientSecret));

        ServiceInstance serviceInstance = loadBalancerClient.choose(AUTH_SERVICE);
        if (serviceInstance == null) {
            throw new RuntimeException("Failed to choose an auth instance.");
        }

        Map<String, Object> map = postForMap(serviceInstance.getUri().toString() + checkTokenEndpointUrl, formData, headers);
        if (map.containsKey("error")) {
            throw new InvalidTokenException(accessToken);
        }
        return tokenConverter.extractAuthentication(map);
    }

    public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    private String getAuthorizationHeader(String clientId, String clientSecret) {
        String creds = String.format("%s:%s", clientId, clientSecret);
        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        Map map = restTemplate.exchange(path, HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(formData, headers), Map.class).getBody();
        Map<String, Object> result = map;
        return result;
    }
}
