package com.job.assignment.integration;

import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import com.job.assignment.bidder.exception.error.RestErrorMessage;
import com.job.assignment.util.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ITBidderEndToEnd implements JsonReader {

    private static final String BID_URL = "http://localhost:9090/embedded/bid";

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }

    /*
        Scenario: Bidder should respond with a bid for the highest paying campaign that matches the targeting criteria.
     */
    @Test
    public void first_integration_test() throws Exception {

        //given
        String sampleBidRequestInJsonFormat = read("files/sample_bid_request_1.json");
        BidRequestDto bidRequestDto = objectMapper.readValue(sampleBidRequestInJsonFormat, BidRequestDto.class);


        //when
        BidResponseDto actual = restTemplate.postForObject("http://localhost:9090/embedded/bid",
                bidRequestDto,
                BidResponseDto.class);


        //then
        TestCase.assertNotNull(actual);

        String sampleBidResponseInJsonFormat = read("files/sample_bid_response_1.json");
        BidResponseDto expected = objectMapper.readValue(sampleBidResponseInJsonFormat, BidResponseDto.class);

        assertThat(actual.getId(), is(expected.getId()));

        assertThat(actual.getBid().getCampaignId(), is(expected.getBid().getCampaignId()));
        assertThat(actual.getBid().getAdm(), is(expected.getBid().getAdm()));
        assertThat(actual.getBid().getPrice(), is(expected.getBid().getPrice()));
    }

    /*
        Scenario: Bidder should respond without a bid if there no available or matching campaigns.
     */
    @Test
    public void second_integration_test() throws Exception {

        //given
        String sampleBidRequestInJsonFormat = read("files/sample_bid_request_2.json");
        BidRequestDto bidRequestDto = objectMapper.readValue(sampleBidRequestInJsonFormat, BidRequestDto.class);

        HttpEntity<BidRequestDto> httpEntity = new HttpEntity<>(bidRequestDto);

        //when
        ResponseEntity<Object> actualResponseEntity = restTemplate.exchange(BID_URL,
                HttpMethod.POST, httpEntity, Object.class);

        //then
        assertThat(actualResponseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
        assertThat(actualResponseEntity.getStatusCodeValue(), is(204));
        TestCase.assertNull(actualResponseEntity.getBody());
    }

    /*
        Scenario: Bidder should respond with BAD_REQUEST when an invalid BidRequestDto send.
     */
    @Test
    public void third_integration_test() throws Exception {

        //given
        String sampleBidRequestInJsonFormat = read("files/sample_bid_request_1.json");
        BidRequestDto bidRequestDto = objectMapper.readValue(sampleBidRequestInJsonFormat, BidRequestDto.class);
        bidRequestDto.setId(null);
        HttpEntity<BidRequestDto> httpEntity = new HttpEntity<>(bidRequestDto);


        try {

            //when
            restTemplate.exchange(BID_URL, HttpMethod.POST, httpEntity, RestErrorMessage.class);

        } catch (RestClientException ex) {

            //then
            Assert.assertTrue(ex instanceof HttpClientErrorException);
            HttpClientErrorException error = (HttpClientErrorException) ex;
            assertThat(error.getResponseBodyAsString(),
                    is("{\"status\":\"BAD_REQUEST\"," +
                            "\"code\":400," +
                            "\"message\":\"Missing id\"," +
                            "\"detailedMessage\":\"Please provide id!\"," +
                            "\"exceptionMessage\":\"com.job.assignment.bidder.exception.ServiceValidationException: Missing id\"}"));
        }


    }
}
