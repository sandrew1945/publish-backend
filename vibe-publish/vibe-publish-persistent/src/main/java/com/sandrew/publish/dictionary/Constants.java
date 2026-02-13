package com.sandrew.publish.dictionary;

public interface Constants
{
    String LOGIN_USER = "loginUser"; // 登录用户
    String APP_KEY = "authc.appKey"; // appKey


    Integer STATUS_ENABLE = Integer.valueOf("10011001"); //有效
    Integer STATUS_DISABLE = Integer.valueOf("10011002"); //无效
    Integer SEX_MALE = Integer.valueOf("10021001"); //男
    Integer SEX_FEMALE = Integer.valueOf("10021002"); //女
    Integer SEX_UNKNOW = Integer.valueOf("10021003"); //未知
    Integer IF_TYPE_YES = Integer.valueOf("10031001"); //是
    Integer IF_TYPE_NO = Integer.valueOf("10031002"); //否
    Integer ACCESS_TYPE_LOGIN = Integer.valueOf("10041001"); //登录
    Integer ACCESS_TYPE_LOGOUT = Integer.valueOf("10041002"); //登出


    Integer STAFF_STATUS_JOB = Integer.valueOf("20011001"); //在职
    Integer STAFF_STATUS_DIMISSION = Integer.valueOf("20011002"); //离职
    Integer STAFF_STATUS_RETIRE = Integer.valueOf("20011003"); //退休
    Integer STAFF_STATUS_RECUPERATE = Integer.valueOf("20011004"); //离岗退养

}
