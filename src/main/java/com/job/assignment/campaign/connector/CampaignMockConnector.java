package com.job.assignment.campaign.connector;

import com.job.assignment.bidder.exception.ServiceBusinessException;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Qualifier("mocked")
public class CampaignMockConnector implements CampaignConnector {

    private static final String CAMPAIGNS_MOCKED_DATA_JSON_FILE = "files/campaigns-mocked-data.json";

    private final ObjectMapper objectMapper;

    @Autowired
    public CampaignMockConnector(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CampaignResponseDto> fetchAll() {

        try {
            ClassLoader classLoader = getClass().getClassLoader();

            URL url = Optional
                    .ofNullable(classLoader.getResource(CAMPAIGNS_MOCKED_DATA_JSON_FILE))
                    .orElseThrow(IllegalStateException::new);

            Path campaignsMockedDataFile = Paths.get(url.toURI());

            String campaignsMockedDataInJsonFormat = Files.lines(campaignsMockedDataFile).collect(Collectors.joining());

            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, CampaignResponseDto.class);

            return objectMapper.readValue(campaignsMockedDataInJsonFormat, collectionType);

        } catch (Exception error) {
            throw new ServiceBusinessException("Campaigns external service connection error",
                    "Could not connect with campaigns external service",
                    error);
        }

    }
}
