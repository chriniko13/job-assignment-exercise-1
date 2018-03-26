package com.job.assignment.bidder.transformer;

import com.job.assignment.bidder.dto.internal.BidResponseTransformerResultDto;
import com.job.assignment.bidder.dto.internal.BidSortedOnPriceTransformerResultDto;
import com.job.assignment.bidder.dto.response.BidInfoResponseDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.job.assignment.util.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BidResponseTransformerTest implements DataGenerator {

    private BidResponseTransformer bidResponseTransformer;

    @Before
    public void setUp() throws Exception {

        bidResponseTransformer = new BidResponseTransformer();
    }

    @Test
    public void transform_has_campaign_response_dto() throws Exception {

        //given
        Map<String, Object> dummyHeaders = generateDummyHeaders();
        String bidRequestDtoId = generateBidRequestDto().getId();
        CampaignResponseDto campaignResponseDto = generateCampaignResponseDto();

        //when
        Message<BidResponseTransformerResultDto> result = bidResponseTransformer.transform(dummyHeaders,
                bidRequestDtoId,
                new BidSortedOnPriceTransformerResultDto(true, campaignResponseDto));

        //then
        BidResponseTransformerResultDto resultPayload = result.getPayload();
        BidResponseDto resultBidResponseDto = resultPayload.getBidResponseDto();
        BidInfoResponseDto resultBid = resultBidResponseDto.getBid();

        assertThat(resultBidResponseDto.getId(), is(bidRequestDtoId));
        assertThat(resultBid.getPrice(), is(campaignResponseDto.getPrice()));
        assertThat(resultBid.getAdm(), is(campaignResponseDto.getAdm()));
        assertThat(resultBid.getCampaignId(), is(campaignResponseDto.getId()));

    }

    @Test
    public void transform_has_not_campaign_response_dto() throws Exception {


        //given
        Map<String, Object> dummyHeaders = generateDummyHeaders();
        String bidRequestDtoId = generateBidRequestDto().getId();

        //when
        Message<BidResponseTransformerResultDto> result = bidResponseTransformer.transform(dummyHeaders,
                bidRequestDtoId,
                new BidSortedOnPriceTransformerResultDto(false));


        //then
        assertThat(result.getPayload().isResult(), is(false));

    }
}