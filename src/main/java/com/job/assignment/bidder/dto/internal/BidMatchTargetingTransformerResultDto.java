package com.job.assignment.bidder.dto.internal;

import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidMatchTargetingTransformerResultDto {

    private boolean result;
    private List<CampaignResponseDto> matchTargetingCampaigns;

    public BidMatchTargetingTransformerResultDto(boolean result) {
        this.result = result;
    }
}
