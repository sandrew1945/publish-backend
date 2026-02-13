package com.sandrew.publish.bean.mailmanager;

import com.sandrew.publish.model.TtMailTemplatePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName TaskManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface MailManagerConvertor
{
    @Mapping(source = "mailManagerBO.templateSubject", target = "templateSubject")
    MailVO toMailVO(MailManagerBO mailManagerBO);

    @Mapping(source = "mailManagerBO.templateSubject", target = "templateSubject")
    TtMailTemplatePO toMailPO(MailManagerBO mailManagerBO);

    @Mapping(source = "mailManagerDTO.templateSubject", target = "templateSubject")
    TtMailTemplatePO toMailPO(MailManagerDTO mailManagerDTO);

    @Mapping(source = "ttMailTemplatePO.templateSubject", target = "templateSubject")
    MailManagerBO toMailManagerBO(TtMailTemplatePO ttMailTemplatePO);

    List<MailVO> toMailVO(List<MailManagerBO> mailManagerBOList);

    List<MailManagerBO> toMailManagerBO(List<TtMailTemplatePO> mailTemplatePOList);
}
