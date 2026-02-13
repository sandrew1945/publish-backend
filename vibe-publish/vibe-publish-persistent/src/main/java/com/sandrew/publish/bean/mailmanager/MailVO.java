package com.sandrew.publish.bean.mailmanager;

import lombok.Data;

@Data
public class MailVO
{
    private Integer templateId;
    private String templateName;
    private String templateSubject;
    private Integer templateStatus;
    private String templateContent;
}
