package org.dragonfei.web;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by longfei on 16-3-14.
 */
public class RandomSalt  implements SaltSource {

    public Object getSalt(UserDetails user) {
        return ((SaltedUser)user).getSalt();
    }
}
