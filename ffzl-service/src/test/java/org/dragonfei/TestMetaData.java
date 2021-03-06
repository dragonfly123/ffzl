package org.dragonfei;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.common.CommonService;
import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.domain.Menu;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ResourceLoaderFactory;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.service.CommonRsEntry;
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
        ServiceResource serviceResource = ResourceLoaderFactory.getResourceLoader("json","serviceinterface").load("ffzl.base");
        ServiceResource serviceResource1 = ResourceLoaderFactory.getResourceLoader("json","sql").load("ffzl.base");
        for(int i =0 ; i < 100;i++) {

            System.out.println(serviceResource.getResourceMap("test"));


            System.out.println(serviceResource1.getResourceMap("test"));
        }
    }

    @Test
    public  void testRecordSet(){
        ParamWrap pw = new ParamWrap();
        pw.setServicename("ffzl_base_test");
        pw.param("createtimeb","2016-05-03").param("createtimee","2016-05-06").param("pageSize","4").param("page","3");
        RecordSet rs = new CommonRsEntry().execute(pw);
        System.out.println(rs);
    }
}
