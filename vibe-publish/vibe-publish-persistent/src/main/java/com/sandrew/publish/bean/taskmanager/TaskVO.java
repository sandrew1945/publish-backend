package com.sandrew.publish.bean.taskmanager;

import lombok.Data;

import java.util.Date;

@Data
public class TaskVO
{
    private Integer taskId;
    private Integer clientId;
    private String clientName;
    private Integer clientType;
    private String clientEmail;
    private String clientMobile;
    private String optName;
    private Integer approveId;
    private String approveName;
    private String taskName;
    private Integer taskType;
    private Integer taskStatus;
    private Integer decDraftFile;
    private Date decDraftSendDate;
    private Integer decFormFile;
    private Date decFormSendDate;
    private String createBy;
    private Date createDate;
    private Date expirationDate;
}
