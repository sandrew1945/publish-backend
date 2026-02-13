package com.sandrew.publish.bean.taskmanager;

import com.sandrew.publish.core.bean.BO;
import lombok.Data;


/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class TaskGenerationBO implements BO
{
    private Integer clientId;
    private String clientName;
    private String type;
    private Integer typeCode;
}
