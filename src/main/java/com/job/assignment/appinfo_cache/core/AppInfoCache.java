package com.job.assignment.appinfo_cache.core;

import com.job.assignment.appinfo_cache.dto.AppInfoDto;

import java.util.Optional;

public interface AppInfoCache {

    Optional<AppInfoDto> get(String name);

}
