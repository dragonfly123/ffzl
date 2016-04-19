package org.dragonfei;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.domain.Menu;
import org.dragonfei.ffzl.utils.number.Numberutils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Collectors;

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

        Assert.assertEquals(commonMetaDataService.getMetaData(Menu.class),
                commonMetaDataService.getMetaData(Menu.class));
    }

    @Test
    public void testBuildSqlParam(){
        Menu menu = new Menu();
        menu.setId(Numberutils.newBigInter(1));
        menu.setText("text");
        long time = Calendar.getInstance().getTimeInMillis();
        SqlParam sqlParam = commonMetaDataService.getSqlParam(menu);
        System.out.println(sqlParam.toString());

        long time2 = Calendar.getInstance().getTimeInMillis();
        System.out.println(time2-time);


        sqlParam = commonMetaDataService.getSqlParam(menu);

        System.out.println(sqlParam.toString());

        System.out.println(Calendar.getInstance().getTimeInMillis()-time2);
    }

    public void testFilter(){

      System.out.print(Arrays.asList(1,2,3,4).stream().filter(a->a%2 == 0).collect(Collectors.toList()));

      System.out.print(Arrays.asList(1,2,3,4).stream().filter(a->a%2 == 0).collect(Collectors.toList()));System.out.print(Arrays.asList(1,2,3,4).stream().filter(a->a%2 == 0).collect(Collectors.toList()));
    }
}
