package org.dragonfei.web;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * Created by longfei on 16-3-19.
 */
public class SingedUsernamePasswordAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public boolean supports(Class<?> authentication) {
        return SignedUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
        SignedUsernamePasswordAuthenticationToken signedToken = (SignedUsernamePasswordAuthenticationToken) authentication;
        if(signedToken.getRequestSignature() == null){
            throw  new BadCredentialsException(messages.getMessage("SignedUsernamePasswordAuthenticationProvider.missingSignature",
                    "Missing request signature"+ (Objects.isNull(userDetails)?null:userDetails)));
        }
        if(!signedToken.getRequestSignature().equals(calculateExpectedSignature(signedToken))){
            throw new BadCredentialsException(messages.getMessage("SignedUsernamePasswordAuthenticationProvider.missingSignature",
                    "Missing request signature"+ (Objects.isNull(userDetails)?null:userDetails)));
        }
    }
    private String calculateExpectedSignature(SignedUsernamePasswordAuthenticationToken sigedtoken){
        return sigedtoken.getPrincipal()+"|+|"+sigedtoken.getCredentials();
    }
}
