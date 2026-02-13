package com.sandrew.publish.bean.taskmanager;

import lombok.Data;

import java.util.Date;

@Data
public class TaskProcessBO
{
    private Integer processId;
    private Integer taskId;
    private String taskName;
    private Date optDate;
    private Integer optBy;
    private String optName;
    private Integer taskStatusFrom;
    private Integer taskStatusTo;
    private String comment;
    private Integer fileId;
    private String fileName;
    private String filePath;
    private String avatar;
}
