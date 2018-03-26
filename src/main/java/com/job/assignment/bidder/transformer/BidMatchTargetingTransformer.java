package com.job.assignment.bidder.transformer;

import com.job.assignment.bidder.dto.internal.BidMatchTargetingTransformerResultDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BidMatchTargetingTransformer {

    public Message<BidMatchTargetingTransformerResultDto> transform(@Header("all-campaigns") List<CampaignResponseDto> allCampaigns,
                                                                    @Headers Map<String, Object> headers,
                                                                    @Payload BidRequestDto bidRequestDto) {

        final String bidCountry = bidRequestDto.getDevice().getGeo().getCountry();

        final List<CampaignResponseDto> matchTargetingCampaigns = allCampaigns
                .stream()
                .filter(campaign -> campaign.getTargetedCountries().contains(bidCountry))
                .collect(Collectors.toList());

        if (allCampaigns.isEmpty()) {
            return MessageBuilder
                    .withPayload(new BidMatchTargetingTransformerResultDto(false))
                    .copyHeadersIfAbsent(headers)
                    .build();
        }

        BidMatchTargetingTransformerResultDto bidMatchTargetingTransformerResultDto
                = new BidMatchTargetingTransformerResultDto(true, matchTargetingCampaigns);

        return MessageBuilder
                .withPayload(bidMatchTargetingTransformerResultDto)
                .copyHeadersIfAbsent(headers)
                .build();
    }
}
