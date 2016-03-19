package org.dragonfei.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by longfei on 16-3-19.
 */
public class RequestHeaderProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private String usernameHeader = "username";
    private String passwordHader = "password";
    private String signatureHeader = "signature";

    protected RequestHeaderProcessingFilter(){
        super("/j_spring_security_filter");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getHeader(usernameHeader);
        String password = request.getHeader(passwordHader);
        String signature = request.getHeader(signatureHeader);

        SignedUsernamePasswordAuthenticationToken authRequest = new SignedUsernamePasswordAuthenticationToken(username,password,signature);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
