package com.sandrew.publish.dictionary;

import com.sandrew.publish.core.enums.BaseEnum;

/**
 * Created by summer on 2018/7/3.
 */
public enum Fixcode implements BaseEnum
{
    STATUS_ENABLE(1001, 10011001, "有效"),
    STATUS_DISABLE(1001, 10011002, "无效"),
    SEX_MALE(1002, 10021001, "男"),
    SEX_FEMALE(1002, 10021002, "女"),
    SEX_UNKNOW(1002, 10021003, "未知"),
    IF_TYPE_YES(1003, 10031001, "是"),
    IF_TYPE_NO(1003, 10031002, "否"),
    ACCESS_TYPE_LOGIN(1004, 10041001, "登录"),
    ACCESS_TYPE_LOGOUT(1004, 10041002, "登出"),

    /*  员工状态  */
    STAFF_STATUS_JOB(2001, 20011001, "在职"),
    STAFF_STATUS_DIMISSION(2001, 20011002, "离职"),
    STAFF_STATUS_RETIRE(2001, 20011003, "退休"),
    STAFF_STATUS_RECUPERATE(2001, 20011004, "离岗退养"),

    DATA_TYPE_STRING(2005, 20051001, "字符串"),
    DATA_TYPE_NUMBER(2005, 20051002, "数字"),
    DATA_TYPE_BOOLEAN(2005, 20051003, "布尔"),
    DATA_TYPE_OBJECT(2005, 20051004, "对象"),
    DATA_TYPE_ARRAY(2005, 20051005, "数组"),
    DATA_TYPE_FUNCTION(2005, 20051006, "函数"),
    DATA_TYPE_REGEXP(2005, 20051007, "正则表达式"),
    DATA_TYPE_NULL(2005, 20051008, "Null"),
    PARAM_TYPE_IN(2006, 20061001, "输入参数"),
    PARAM_TYPE_OUT(2006, 20061002, "输出参数"),
    PARAM_TYPE_COMMON(2006, 20061003, "通用参数"),
    PARAM_TYPE_HEADER(2006, 20061004, "Header参数"),
    INVOKE_TYPE_GET(2007, 20071001, "GET请求"),
    INVOKE_TYPE_POST(2007, 20071002, "POST请求"),
    INVOKE_TYPE_PUT(2007, 20071003, "PUT请求"),
    INVOKE_TYPE_DELETE(2007, 20071004, "DELETE请求"),
    INVOKE_TYPE_OPTIONS(2007, 20071005, "OPTIONS请求"),
    INVOKE_TYPE_PATCH(2007, 20071006, "PATCH请求"),
    INVOKE_TYPE_HEAD(2007, 20071007, "HEAD请求"),
    STATUS_CODE_200(2008, 20081001, "200"),
    STATUS_CODE_301(2008, 20081002, "301"),
    STATUS_CODE_401(2008, 20081003, "401"),
    STATUS_CODE_403(2008, 20081004, "403"),
    STATUS_CODE_404(2008, 20081005, "404"),
    STATUS_CODE_500(2008, 20081006, "500"),
    STATUS_CODE_503(2008, 20081007, "503"),
    STATUS_CODE_504(2008, 20081008, "504"),
    PINGPONG_STATUS_UNREAD(2010, 20101001, "未读"),
    PINGPONG_STATUS_READ(2010, 20101002, "已读"),
    PINGPONG_STATUS_RESOLVE(2010, 20101003, "已解决"),
    PINGPONG_STATUS_CLOSE(2010, 20101004, "关闭");


    public Integer type;       // 字典类别
    public Integer fixcode;     // 字典码
    public String desc;        // 描述
    private Fixcode(Integer type, Integer fixcode, String desc)
    {
        this.type = type;
        this.fixcode = fixcode;
        this.desc = desc;
    }

    public Integer getType()
    {
        return type;
    }

    public Integer getCode()
    {
        return fixcode;
    }

    public String getDesc()
    {
        return desc;
    }
}