package org.dragonfei.web;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by longfei on 16-3-8.
 */
public interface IChangePassword extends UserDetailsService {
    void changePassword(String username,String password);
}
