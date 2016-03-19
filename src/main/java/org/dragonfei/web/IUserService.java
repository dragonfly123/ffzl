package org.dragonfei.web;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by longfei on 16-3-15.
 */
public interface IUserService {

    void changePassword(String username,String  password);
}
