package org.dragonfei;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.common.CommonService;
import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.domain.Menu;
import org.dragonfei.ffzl.params.support.ResourceLoader;
import org.dragonfei.ffzl.params.support.ServiceContext;
import org.dragonfei.ffzl.utils.number.Numberutils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Arrays;
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
        for (int i =  0; i < 100 ; i  ++) {
            Menu menu = new Menu();
            menu.setId(Numberutils.newBigInter(1));
            menu.setText("text");
            menu.setAddr("http://www.baidu.com");
            menu.setPid(Numberutils.newBigInter(5));
            MetaData metaData = commonMetaDataService.getMetaData(Menu.class);
            SqlParam sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.INSERT);
            System.out.println(sqlParam.toString());

            metaData = commonMetaDataService.getMetaData(Menu.class);
            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.INSERT);

            System.out.println(sqlParam.toString());

            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.DELETE);
            System.out.println(sqlParam.toString());
            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.DELETE);
            System.out.println(sqlParam.toString());

            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.SELECT);
            System.out.println(sqlParam.toString());
            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.SELECT);
            System.out.println(sqlParam.toString());


            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.UPDATE);
            System.out.println(sqlParam.toString());
            sqlParam = commonMetaDataService.getSqlParam(menu, metaData, CommonMetaDataService.SqlType.UPDATE);
            System.out.println(sqlParam.toString());
        }

    }


    @Test
    public void testResource2(){
        ServiceContext context = ResourceLoader.load("ffzl_base_test");

        System.out.println(context);
    }
    public void testFilter(){

      System.out.print(Arrays.asList(1,2,3,4).stream().filter(a->a%2 == 0).collect(Collectors.toList()));

      System.out.print(Arrays.asList(1,2,3,4).stream().filter(a->a%2 == 0).collect(Collectors.toList()));System.out.print(Arrays.asList(1,2,3,4).stream().filter(a->a%2 == 0).collect(Collectors.toList()));
    }
    @Autowired CommonService commonService;
    @Test
    public void testServiceImpl(){
        System.out.println(commonService.select(new Menu()));
    }

    @Test
    public void testResource(){
        for (int i =0; i < 100; i++) {
            ServiceContext sc = ResourceLoader.load("ffzl.base");
            System.out.println(sc);
        }
    }
}
