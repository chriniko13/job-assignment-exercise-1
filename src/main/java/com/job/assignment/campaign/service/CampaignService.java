package com.job.assignment.campaign.service;

import com.job.assignment.campaign.dto.response.CampaignResponseDto;

import java.util.List;

public interface CampaignService {

    List<CampaignResponseDto> findAll();
}
