package com.job.assignment.bidder.resource;

import com.job.assignment.bidder.dto.internal.BidResponseTransformerResultDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import com.job.assignment.bidder.gateway.BidGateway;
import com.job.assignment.bidder.validator.Validator;
import com.job.assignment.util.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BidResourceImplTest implements DataGenerator {

    @Mock
    private BidGateway bidGateway;

    @Mock
    private Validator<BidRequestDto> bidRequestDtoValidator;

    private BidResource bidResource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bidResource = new BidResourceImpl(bidGateway, bidRequestDtoValidator);
    }

    @Test
    public void bid() throws Exception {

        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        BidResponseDto bidResponseDto = generateBidResponseDto();
        BidResponseTransformerResultDto bidResponseTransformerResultDto = new BidResponseTransformerResultDto(true, bidResponseDto);

        Mockito.when(bidGateway.bid(Mockito.isA(BidRequestDto.class))).thenReturn(bidResponseTransformerResultDto);

        //when
        ResponseEntity<BidResponseDto> result = bidResource.bid(bidRequestDto);

        //then
        assertThat(result.getBody().getId(), is(bidResponseDto.getId()));
        assertThat(result.getBody().getBid().getAdm(), is(bidResponseDto.getBid().getAdm()));
        assertThat(result.getBody().getBid().getCampaignId(), is(bidResponseDto.getBid().getCampaignId()));
        assertThat(result.getBody().getBid().getPrice(), is(bidResponseDto.getBid().getPrice()));

        Mockito.verify(bidGateway, Mockito.times(1)).bid(Mockito.isA(BidRequestDto.class));
        Mockito.verify(bidRequestDtoValidator, Mockito.times(1)).validate(Mockito.isA(BidRequestDto.class));
    }

}