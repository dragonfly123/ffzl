package org.dragonfei.web;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by longfei on 16-3-12.
 */
public class CustomJdbcaoImpl extends JdbcDaoImpl implements IChangePassword {
    public void changePassword(String username, String password) {
        getJdbcTemplate().update("UPDATE USERS SET PASSWORD = ? WHERE USERNAME  = ?",password,username);
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
                                            List<GrantedAuthority> combinedAuthorities) {
        String returnUsername = userFromUserQuery.getUsername();
        if(!isUsernameBasedPrimaryKey()){
            returnUsername = username;
        }
        return new SaltedUser(returnUsername,userFromUserQuery.getPassword(),userFromUserQuery.isEnabled(),
                true,true,true,combinedAuthorities,((SaltedUser)userFromUserQuery).getSalt());
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {

        return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[]{username}, new RowMapper<UserDetails>() {
            public UserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                boolean enabled = resultSet.getBoolean(3);
                String salt = resultSet.getString(4);
                return new SaltedUser(username,password,enabled,true,true,true, AuthorityUtils.NO_AUTHORITIES,salt);
            }
        });
    }
}
