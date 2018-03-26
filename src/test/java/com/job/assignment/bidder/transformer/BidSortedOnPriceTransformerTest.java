package com.job.assignment.bidder.transformer;

import com.job.assignment.bidder.dto.internal.BidMatchTargetingTransformerResultDto;
import com.job.assignment.bidder.dto.internal.BidSortedOnPriceTransformerResultDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.job.assignment.util.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BidSortedOnPriceTransformerTest implements DataGenerator {

    private BidSortedOnPriceTransformer bidSortedOnPriceTransformer;

    @Before
    public void setUp() throws Exception {
        bidSortedOnPriceTransformer = new BidSortedOnPriceTransformer();
    }

    @Test
    public void transform_has_result_in_order_to_elect_max_price() throws Exception {

        //given
        Map<String, Object> dummyHeaders = generateDummyHeaders();
        List<CampaignResponseDto> campaignResponseDtos = generateCampaignResponseDtos();
        CampaignResponseDto maxPriceCampaignResponseDto = generateCampaignResponseDto(3);

        //when
        Message<BidSortedOnPriceTransformerResultDto> result = bidSortedOnPriceTransformer.transform(dummyHeaders,
                new BidMatchTargetingTransformerResultDto(true, campaignResponseDtos));


        //then
        BidSortedOnPriceTransformerResultDto resultPayload = result.getPayload();
        CampaignResponseDto resultCampaignResponseDto = resultPayload.getCampaignResponseDto();

        assertThat(resultCampaignResponseDto.getId(), is(maxPriceCampaignResponseDto.getId()));
        assertThat(resultCampaignResponseDto.getPrice(), is(maxPriceCampaignResponseDto.getPrice()));
        assertThat(resultCampaignResponseDto.getName(), is(maxPriceCampaignResponseDto.getName()));
        assertThat(resultCampaignResponseDto.getAdm(), is(maxPriceCampaignResponseDto.getAdm()));
        assertThat(resultCampaignResponseDto.getTargetedCountries(), is(maxPriceCampaignResponseDto.getTargetedCountries()));

    }

    @Test
    public void transform_has_not_result_in_order_to_elect_max_price() throws Exception {

        //given
        Map<String, Object> dummyHeaders = generateDummyHeaders();
        CampaignResponseDto maxPriceCampaignResponseDto = generateCampaignResponseDto(3);

        //when
        Message<BidSortedOnPriceTransformerResultDto> result = bidSortedOnPriceTransformer.transform(dummyHeaders,
                new BidMatchTargetingTransformerResultDto(false));

        //then
        BidSortedOnPriceTransformerResultDto resultPayload = result.getPayload();
        boolean resultPayloadResult = resultPayload.isResult();

        assertThat(resultPayloadResult, is(false));
    }

}