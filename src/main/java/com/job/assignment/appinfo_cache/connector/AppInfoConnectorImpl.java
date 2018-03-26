package com.job.assignment.appinfo_cache.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.job.assignment.appinfo_cache.dto.AppInfoDto;
import com.job.assignment.bidder.exception.ServiceBusinessException;
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
public class AppInfoConnectorImpl implements AppInfoConnector {

    private final ObjectMapper objectMapper;
    private List<AppInfoDto> appInfoDtos;

    @Autowired
    public AppInfoConnectorImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        loadMockedData();
    }

    private void loadMockedData() {
        try {
            //load mocked data...
            ClassLoader classLoader = getClass().getClassLoader();

            URL url = Optional
                    .ofNullable(classLoader.getResource("files/app-info-mocked.data.json"))
                    .orElseThrow(IllegalStateException::new);

            Path appInfosMockedDataFile = Paths.get(url.toURI());

            String appInfosMockedDataInJsonFormat = Files.lines(appInfosMockedDataFile).collect(Collectors.joining());

            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, AppInfoDto.class);

            appInfoDtos = objectMapper.readValue(appInfosMockedDataInJsonFormat, collectionType);

        } catch (Exception error) {
            throw new ServiceBusinessException("AppInfos external service connection error",
                    "Could not connect with appinfos external service",
                    error);

        }
    }

    @Override
    public AppInfoDto get(String name) {
        return appInfoDtos
                .stream()
                .filter(appInfoDto -> appInfoDto.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
