package com.sandrew.publish.bean.taskmanager;

import com.sandrew.publish.model.TmTaskPO;
import com.sandrew.publish.model.TtTaskPO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName TaskManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface TaskManagerConvertor
{
    TaskVO toTaskVO(TaskManagerBO TaskManagerBO);

    TtTaskPO toTaskPO(TaskManagerBO TaskManagerBO);

//    TtTaskPO toTaskPO(TaskManagerDTO TaskManagerDTO);

    TaskManagerBO toTaskManagerBO(TmTaskPO tmTaskPO);

    List<TaskVO> toTaskVO(List<TaskManagerBO> TaskManagerBOList);
}
