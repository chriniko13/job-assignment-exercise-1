package com.job.assignment.bidder.transformer;

import com.job.assignment.bidder.dto.internal.BidResponseTransformerResultDto;
import com.job.assignment.bidder.dto.internal.BidSortedOnPriceTransformerResultDto;
import com.job.assignment.bidder.dto.response.BidInfoResponseDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BidResponseTransformer {

    public Message<BidResponseTransformerResultDto> transform(@Headers Map<String, Object> headers,
                                                              @Header("bid-request-dto-id") String bidRequestDtoId,
                                                              @Payload BidSortedOnPriceTransformerResultDto bidSortedOnPriceTransformerResultDto) {

        if (!bidSortedOnPriceTransformerResultDto.isResult()) {
            return MessageBuilder
                    .withPayload(new BidResponseTransformerResultDto(false))
                    .copyHeadersIfAbsent(headers)
                    .build();
        }

        final CampaignResponseDto campaignResponseDto = bidSortedOnPriceTransformerResultDto.getCampaignResponseDto();

        BidInfoResponseDto bidInfoResponseDto
                = new BidInfoResponseDto(campaignResponseDto.getId(), campaignResponseDto.getPrice(), campaignResponseDto.getAdm());

        BidResponseDto bidResponseDto = new BidResponseDto(bidRequestDtoId, bidInfoResponseDto);

        BidResponseTransformerResultDto bidResponseTransformerResultDto
                = new BidResponseTransformerResultDto(true, bidResponseDto);

        return MessageBuilder
                .withPayload(bidResponseTransformerResultDto)
                .copyHeadersIfAbsent(headers)
                .build();

    }

}
