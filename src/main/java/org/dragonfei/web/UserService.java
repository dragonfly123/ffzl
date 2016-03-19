package org.dragonfei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by longfei on 16-3-15.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private CustomJdbcaoImpl customJdbcao ;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void changePassword(String oldPassword, String password) {
        customJdbcao.changePassword(oldPassword,password);

    }
}
