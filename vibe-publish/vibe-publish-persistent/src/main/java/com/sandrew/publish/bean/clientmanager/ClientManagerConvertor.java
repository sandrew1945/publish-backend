package com.sandrew.publish.bean.clientmanager;


import com.sandrew.publish.model.TmClientPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName RoleManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface ClientManagerConvertor
{
    ClientVO toClientVO(ClientManagerBO clientManagerBO);



    @Mapping(source = "clientManagerBO.serviceStart", target = "serviceStart")
    @Mapping(source = "clientManagerBO.serviceEnd", target = "serviceEnd")
    TmClientPO toClientPO(ClientManagerBO clientManagerBO);

    @Mapping(source = "clientManagerDTO.serviceStart", target = "serviceStart")
    @Mapping(source = "clientManagerDTO.serviceEnd", target = "serviceEnd")
    TmClientPO toClientPO(ClientManagerDTO clientManagerDTO);

    @Mapping(source = "tmClientPO.serviceStart", target = "serviceStart")
    @Mapping(source = "tmClientPO.serviceEnd", target = "serviceEnd")
    @Mapping(source = "tmClientPO.clientLinkman", target = "clientLinkman")
    @Mapping(target = "tmClientPO.declarePeriod", ignore = true)
    ClientManagerBO toClientManagerBO(TmClientPO tmClientPO);

    List<ClientVO> toClientVO(List<ClientManagerBO> clientManagerBOList);
}
