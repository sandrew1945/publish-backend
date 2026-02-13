package com.sandrew.publish.bean.clientmanager;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientManagerDTO
{
    private Integer clientId;
    private String clientName;
    private Integer clientType;
//    private Integer declarePeriod;
    private Integer optId;
    private String optName;
    private Integer approveId;
    private String approveName;
    private String clientIct;
    private String clientVat;
    private LocalDate serviceStart;
    private LocalDate serviceEnd;
    private String clientTaxNo;
    private String clientKbk;
    private String clientFee;
    private String clientMobile;
    private String clientLinkman;
    private String clientEmail;
    private String clientAddress;
    private String clientPostcode;
    private String clientCity;
    private String description;
}
