package com.myweb.content.filter;

import com.myweb.content.config.oauth2.TokenCheckService;
import com.myweb.content.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecurityContextFilter implements Filter {


    @Autowired
    private TokenCheckService tokenCheckService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String token = request.getHeader("token");
            OAuth2Authentication authentication = tokenCheckService.loadAuthentication(token);

            UserContext userContext = new UserContext();
            userContext.setAuthorities(authentication.getAuthorities());
            userContext.setUsername(authentication.getName());

            if (SecurityContextHolder.getContext() != null) {
                SecurityContextHolder.clearContext();
            }

            UsernamePasswordAuthenticationToken authenticationToke = new UsernamePasswordAuthenticationToken(userContext,
                    null, userContext.getAuthorities());
            userContext.setAuthentication(authenticationToke);
            SecurityContextHolder.setContext(userContext);
        } catch (Exception e) {

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
