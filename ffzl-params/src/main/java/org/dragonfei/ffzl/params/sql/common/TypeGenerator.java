package org.dragonfei.ffzl.params.sql.common;

import java.sql.JDBCType;
import java.util.List;

/**
 * Created by longfei on 16-6-19.
 */
public class TypeGenerator {
    public static int[] commonTypes(List parameters){
        int[] types = new int[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            types[i] = JDBCType.VARCHAR.getVendorTypeNumber();
        }
        return types;
    }

    public static int[] pageTypes(List parameters){
        int[] types = new int[parameters.size()];
        for (int i = 0; i < parameters.size() - 2; i++) {
            types[i] = JDBCType.VARCHAR.getVendorTypeNumber();
        }
        types[parameters.size() - 2] = JDBCType.INTEGER.getVendorTypeNumber();
        types[parameters.size() - 1] = JDBCType.INTEGER.getVendorTypeNumber();
        return types;
    }


}
