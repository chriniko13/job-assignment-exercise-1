package com.job.assignment.campaign.connector;

import com.job.assignment.campaign.dto.response.CampaignResponseDto;

import java.util.List;

public interface CampaignConnector {

    List<CampaignResponseDto> fetchAll();
}
