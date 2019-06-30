package com.sh.shdemo.Util;

public class pageUtil {
    public static int countPage(int count,int oneCount){
        return count%oneCount==0?count/oneCount:(count/oneCount)+1;
    }
}
