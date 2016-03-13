package org.dragonfei.web;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * Created by longfei on 16-3-12.
 */
public class CustomJdbcaoImpl extends JdbcDaoImpl implements IChangePassword {
    public void changePassword(String username, String password) {
        getJdbcTemplate().update("UPDATE USERS SET PASSWORD = ? WHERE USERNAME  = ?",password,username);
    }
}
