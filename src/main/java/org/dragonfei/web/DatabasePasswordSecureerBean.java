package org.dragonfei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by longfei on 16-3-13.
 */
public class DatabasePasswordSecureerBean extends JdbcDaoSupport {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired  private UserDetailsService userDetailsService;


    public void secureDatabase(){
        getJdbcTemplate().query("select username,password from users", new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                String username = resultSet.getString(1);
                String  password  = resultSet.getString(2);

                UserDetails user = userDetailsService.loadUserByUsername(username);
                String endcodedPassword = passwordEncoder.encodePassword(password,((SaltedUser)user).getSalt());

                getJdbcTemplate().update("UPDATE users set password = ? where username = ?",endcodedPassword,username);
            }
        });
    }
}
