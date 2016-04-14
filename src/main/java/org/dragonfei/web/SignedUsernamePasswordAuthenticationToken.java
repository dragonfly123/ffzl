package org.dragonfei.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by longfei on 16-3-19.
 */
public class SignedUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken{
    private String requestSignature;
    private static final Long serialVersionUID = 314555555555555555l;

    public SignedUsernamePasswordAuthenticationToken(Object principal, String credentials, String requestSignature) {
        super(principal, credentials);
        this.requestSignature = requestSignature;
    }

    public String getRequestSignature() {
        return requestSignature;
    }

    public void setRequestSignature(String requestSignature) {
        this.requestSignature = requestSignature;
    }
}
