package org.dragonfei.ffzl.utils.number;

import java.math.BigInteger;

/**
 * Created by longfei on 16-4-10.
 */
public abstract class Numberutils {
    public static BigInteger newBigInter(int bigint){
        return new BigInteger(String.valueOf(bigint),10);
    }

    public static BigInteger newBigInter(String bigint){
        return new BigInteger(bigint,10);
    }
}
