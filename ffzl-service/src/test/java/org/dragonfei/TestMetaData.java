package org.dragonfei;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.ffzl.domain.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;

/**
 * Created by admin on 16/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-*.xml")
public class TestMetaData {
    @Autowired CommonMetaDataService commonMetaDataService;
    @Test
    public void testMetaData(){
        Menu menu = new Menu();
        menu.setId(new BigInteger("1"));
        System.out.println(commonMetaDataService.getMetaData(Menu.class));
        System.out.print(commonMetaDataService.getMetaData(Menu.class));
    }
}
