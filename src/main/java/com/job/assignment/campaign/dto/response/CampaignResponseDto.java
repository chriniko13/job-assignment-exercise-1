package com.job.assignment.campaign.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CampaignResponseDto {

    private String id;
    private String name;
    private BigDecimal price;
    private String adm;
    private List<String> targetedCountries;

}
