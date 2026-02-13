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

    CROP_TYPE_EMZ(2002, 20021001, "Eenmanzaak"),
    CROP_TYPE_BV(2002, 20021002, "B.V."),

    DEC_PERIOD_M(2003, 20031001, "月报"),
    DEC_PERIOD_Q(2003, 20031002, "季报"),

    ROLE_TYPE_SYSTEM(2004, 20041001, "系统管理"),
    ROLE_TYPE_ADMIN(2004, 20041002, "公司管理"),
    ROLE_TYPE_STAFF(2004, 20041003, "公司员工"),

    TASK_TYPE_M(2005, 20051001, "月度任务"),
    TASK_TYPE_Q(2005, 20051002, "季度任务"),
    TASK_TYPE_Y(2005, 20051003, "年度任务"),

    TASK_STATUS_UNPROCESSED(2006, 20061001, "未处理"),
    TASK_STATUS_PROCESSING(2006, 20061002, "账务处理"),
    TASK_STATUS_SELF_EXAM(2006, 20061003, "自审完成"),
    TASK_STATUS_APPROVED(2006, 20061004, "审核通过"),
    TASK_STATUS_DRAFT(2006, 20061005, "草稿发送"),
    TASK_STATUS_INNER_REJ(2006, 20061006, "内部驳回"),
    TASK_STATUS_UNDECLARATION(2006, 20061007, "等待申报"),
    TASK_STATUS_OUTER_REJ(2006, 20061008, "外部驳回"),
    TASK_STATUS_SUBMIT(2006, 20061009, "提交申报"),
    DEC_TYPE_N(2007, 20071001, "无"),
    DEC_TYPE_M(2007, 20071002, "月度"),
    DEC_TYPE_Q(2007, 20071003, "季度");



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