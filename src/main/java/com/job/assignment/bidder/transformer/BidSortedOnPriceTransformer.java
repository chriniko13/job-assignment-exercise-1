package com.job.assignment.bidder.transformer;

import com.job.assignment.bidder.dto.internal.BidMatchTargetingTransformerResultDto;
import com.job.assignment.bidder.dto.internal.BidSortedOnPriceTransformerResultDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class BidSortedOnPriceTransformer {

    public Message<BidSortedOnPriceTransformerResultDto> transform(@Headers Map<String, Object> headers,
                                                                   @Payload BidMatchTargetingTransformerResultDto bidMatchTargetingTransformerResultDto) {

        BidSortedOnPriceTransformerResultDto bidSortedOnPriceTransformerResultDto = Optional
                .of(bidMatchTargetingTransformerResultDto)
                .filter(BidMatchTargetingTransformerResultDto::isResult)
                .map(BidMatchTargetingTransformerResultDto::getMatchTargetingCampaigns)
                .flatMap(maxPriceElector())
                .map(maxCampaingResponseDto -> new BidSortedOnPriceTransformerResultDto(true, maxCampaingResponseDto))
                .orElseGet(() -> new BidSortedOnPriceTransformerResultDto(false));

        return MessageBuilder
                .withPayload(bidSortedOnPriceTransformerResultDto)
                .copyHeadersIfAbsent(headers)
                .build();
    }

    private Function<List<CampaignResponseDto>, Optional<CampaignResponseDto>> maxPriceElector() {
        return campaignResponseDtos -> campaignResponseDtos.stream().max(Comparator.comparing(CampaignResponseDto::getPrice));
    }

}
