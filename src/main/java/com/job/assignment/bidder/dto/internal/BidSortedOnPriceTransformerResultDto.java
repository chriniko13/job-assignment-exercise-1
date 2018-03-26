package com.job.assignment.bidder.dto.internal;

import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidSortedOnPriceTransformerResultDto {

    private boolean result;
    private CampaignResponseDto campaignResponseDto;

    public BidSortedOnPriceTransformerResultDto(boolean result) {
        this.result = result;
    }
}
