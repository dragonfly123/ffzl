package org.dragonfei.web;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by longfei on 16-3-16.
 */
public class IPRoleAuthenticationFilter extends OncePerRequestFilter {

    private String targetRole;
    private List<String> allowedIpAddress;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!Objects.isNull(authentication) && !Objects.isNull(targetRole)){
            boolean shouldCheck  = false;
            for(GrantedAuthority  authority :authentication.getAuthorities()){
                if(ObjectUtils.nullSafeEquals(authority.getAuthority(),targetRole)){
                    shouldCheck = true;
                    break;
                }
            }

            if(shouldCheck && allowedIpAddress.size() >  0){
                boolean shouldAllow = false;
                for(String ipAddress : allowedIpAddress){
                    if(ObjectUtils.nullSafeEquals(request.getRemoteAddr(),ipAddress)){
                        shouldAllow  = true;
                        break;
                    }
                }
                if(!shouldAllow){
                    throw new AccessDeniedException("Access has been denied for your IP address:"+request.getRemoteAddr());
                }
            }

        }  else  {

            LoggerFactory.getLogger(this.getClass()).warn("Then IPRoleAuthenticationFilter " +
                    "shouled be placed after the user has been authenticated in the filter chain");

        }
        filterChain.doFilter(request,response);
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public List<String> getAllowedIpAddress() {
        return allowedIpAddress;
    }

    public void setAllowedIpAddress(List<String> allowedIpAddress) {
        this.allowedIpAddress = allowedIpAddress;
    }
}
