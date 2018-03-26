package com.job.assignment.campaign.connector;

import com.job.assignment.bidder.exception.ServiceBusinessException;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.job.assignment.util.DataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CampaignMockConnectorTest implements DataGenerator {

    private CampaignConnector campaignConnector;

    // create a rule for an exception grabber that you can use across
    // the methods in this test class
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        campaignConnector = new CampaignMockConnector(new ObjectMapper());
    }

    @Test
    public void fetchAll() throws Exception {

        //given
        List<CampaignResponseDto> campaignResponseDtos = generateCampaignResponseDtos();

        //when
        List<CampaignResponseDto> result = campaignConnector.fetchAll();

        //then
        assertThat(result.size(), equalTo(campaignResponseDtos.size()));

        for (int i = 0; i < result.size(); i++) {

            CampaignResponseDto actual = result.get(i);
            CampaignResponseDto expected = campaignResponseDtos.get(i);

            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getAdm(), is(expected.getAdm()));
            assertThat(actual.getName(), is(expected.getName()));
            assertThat(actual.getPrice(), is(expected.getPrice()));
            assertThat(actual.getTargetedCountries(), is(expected.getTargetedCountries()));
        }

    }

    @Test
    public void fetchAll_fail_scenario() throws Exception {

        //given
        ObjectMapper mockedObjectMapper = Mockito.mock(ObjectMapper.class);
        Mockito.when(mockedObjectMapper.getTypeFactory()).thenThrow(IllegalStateException.class);
        campaignConnector = new CampaignMockConnector(mockedObjectMapper);

        exceptionGrabber.expect(ServiceBusinessException.class); //then

        campaignConnector.fetchAll(); //when

    }

}