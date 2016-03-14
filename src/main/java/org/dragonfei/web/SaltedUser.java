package org.dragonfei.web;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.List;

/**
 * Created by longfei on 16-3-13.
 */
public class SaltedUser extends User{
    private String salt;

    public SaltedUser(String username, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNoExpired, boolean accountNonLocked, List<GrantedAuthority> authorities,
                      String salt){
        super(username,password,enabled,accountNonExpired,credentialsNoExpired,accountNonLocked,authorities);
        this.salt = salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

}
