package com.job.assignment.campaign.service;

import com.job.assignment.campaign.connector.CampaignConnector;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignConnector campaignConnector;

    @Autowired
    public CampaignServiceImpl(@Qualifier("mocked") CampaignConnector campaignConnector) {
        this.campaignConnector = campaignConnector;
    }

    @Override
    public List<CampaignResponseDto> findAll() {
        return campaignConnector.fetchAll();
    }

}
