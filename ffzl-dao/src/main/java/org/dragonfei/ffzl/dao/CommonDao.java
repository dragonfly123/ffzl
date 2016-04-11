package org.dragonfei.ffzl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by longfei on 16-4-9.
 */

@Repository
public class CommonDao {

    @Autowired  @Qualifier("ffzltemplate")  private JdbcTemplate template;
    public void insert(){
        String sql = "insert into T_Users(username,password,salt) values('longfei','longfei135','123')";
        template.execute(sql);
    }
}
