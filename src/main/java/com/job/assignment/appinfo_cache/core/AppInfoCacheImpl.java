package com.job.assignment.appinfo_cache.core;

import com.job.assignment.appinfo_cache.connector.AppInfoConnector;
import com.job.assignment.appinfo_cache.dto.AppInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppInfoCacheImpl implements AppInfoCache {

    private final Map<String, Optional<AppInfoDto>> nameToAppInfoDtoBindings;

    private final AppInfoConnector appInfoConnector;

    @Autowired
    public AppInfoCacheImpl(@Qualifier("mocked") AppInfoConnector appInfoConnector) {
        this.appInfoConnector = appInfoConnector;
        nameToAppInfoDtoBindings = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<AppInfoDto> get(String name) {
        return nameToAppInfoDtoBindings
                .computeIfAbsent(
                        name,
                        _name -> Optional.ofNullable(appInfoConnector.get(_name))
                );
    }
}
