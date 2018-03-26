package com.job.assignment.bidder.transformer;

import com.job.assignment.bidder.dto.internal.BidMatchTargetingTransformerResultDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.job.assignment.util.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BidMatchTargetingTransformerTest implements DataGenerator {

    private BidMatchTargetingTransformer bidMatchTargetingTransformer;

    @Before
    public void setUp() throws Exception {
        bidMatchTargetingTransformer = new BidMatchTargetingTransformer();
    }

    @Test
    public void transform_all_campaigns_list_not_empty() throws Exception {

        //given
        Map<String, Object> dummyHeaders = generateDummyHeaders();
        List<CampaignResponseDto> campaignResponseDtos = generateCampaignResponseDtos();
        BidRequestDto bidRequestDto = generateBidRequestDto();

        //when
        Message<BidMatchTargetingTransformerResultDto> result = bidMatchTargetingTransformer.transform(campaignResponseDtos, dummyHeaders, bidRequestDto);

        //then
        BidMatchTargetingTransformerResultDto resultPayload = result.getPayload();
        List<CampaignResponseDto> resultMatchTargetingCampaigns = resultPayload.getMatchTargetingCampaigns();

        assertThat(resultMatchTargetingCampaigns.size(), is(2));

        for (int i = 0; i < resultMatchTargetingCampaigns.size(); i++) {

            CampaignResponseDto campaignResponseDto = resultMatchTargetingCampaigns.get(i);
            String bidRequestDtoCountry = bidRequestDto.getDevice().getGeo().getCountry();

            assertThat(campaignResponseDto.getTargetedCountries().contains(bidRequestDtoCountry), is(true));

        }

    }

    @Test
    public void transform_all_campaigns_list_empty() throws Exception {

        //given
        Map<String, Object> dummyHeaders = generateDummyHeaders();
        List<CampaignResponseDto> campaignResponseDtos = Collections.emptyList();
        BidRequestDto bidRequestDto = generateBidRequestDto();

        //when
        Message<BidMatchTargetingTransformerResultDto> result = bidMatchTargetingTransformer.transform(campaignResponseDtos, dummyHeaders, bidRequestDto);

        //then
        BidMatchTargetingTransformerResultDto resultPayload = result.getPayload();

        assertThat(resultPayload.isResult(), is(false));

    }
}