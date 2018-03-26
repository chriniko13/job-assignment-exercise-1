package com.job.assignment.campaign.service;

import com.job.assignment.campaign.connector.CampaignConnector;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.job.assignment.util.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CampaignServiceImplTest implements DataGenerator {

    private CampaignService campaignService;

    @Mock
    private CampaignConnector mockedCampaignConnector;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        campaignService = new CampaignServiceImpl(mockedCampaignConnector);
    }

    @Test
    public void findAll() throws Exception {

        //given
        CampaignResponseDto campaignResponseDto = generateCampaignResponseDto();

        Mockito.when(mockedCampaignConnector.fetchAll())
                .thenReturn(Collections.singletonList(campaignResponseDto));

        //when
        List<CampaignResponseDto> result = campaignService.findAll();

        //then
        assertThat(result.size(), is(1));

        CampaignResponseDto resultDto = result.get(0);

        assertThat(resultDto.getId(), is(campaignResponseDto.getId()));
        assertThat(resultDto.getAdm(), is(campaignResponseDto.getAdm()));
        assertThat(resultDto.getName(), is(campaignResponseDto.getName()));
        assertThat(resultDto.getPrice(), is(campaignResponseDto.getPrice()));
        assertThat(resultDto.getTargetedCountries(), is(campaignResponseDto.getTargetedCountries()));

        Mockito.verify(mockedCampaignConnector, Mockito.times(1)).fetchAll();
    }

}