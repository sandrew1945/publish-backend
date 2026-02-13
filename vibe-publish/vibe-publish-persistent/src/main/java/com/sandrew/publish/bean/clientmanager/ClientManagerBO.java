package com.sandrew.publish.bean.clientmanager;

import com.sandrew.publish.core.bean.BO;
import lombok.Data;

import java.time.LocalDate;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class ClientManagerBO implements BO
{
    private Integer clientId;
    private String clientName;
    private Integer clientType;
//    private Integer declarePeriod;
    private String clientIct;
    private String clientVat;
    private Integer optId;
    private String optName;
    private Integer approveId;
    private String approveName;
    private LocalDate serviceStart;
    private LocalDate serviceEnd;
    private String clientTaxNo;
    private String clientKbk;
    private String clientMobile;
    private String clientLinkman;
    private String clientEmail;
    private String clientAddress;
    private String clientPostcode;
    private String clientCity;
    private String description;
}
