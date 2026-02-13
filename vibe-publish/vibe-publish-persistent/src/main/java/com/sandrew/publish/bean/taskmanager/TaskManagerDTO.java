package com.sandrew.publish.bean.taskmanager;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskManagerDTO
{
    private Integer taskId;
    private String clientName;
    private Integer clientType;
    private String taskName;
    private Integer taskType;
    private List<Integer> taskStatus;
    private Date docArchiveDate;
    private Date bankNotesArchiveDate;
    private Date selfExamArchiveDate;
    private String startFrom;
    private String endWith;
    private Date draftCreateDate;
    private String dirRejCmnt;
    private String cliRejCmnt;
    private Integer optId;
    private Integer approveId;
}
