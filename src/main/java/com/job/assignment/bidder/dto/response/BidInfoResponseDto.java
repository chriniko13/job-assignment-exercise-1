package com.job.assignment.bidder.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidInfoResponseDto {

    private String campaignId;
    private BigDecimal price;
    private String adm;

}
