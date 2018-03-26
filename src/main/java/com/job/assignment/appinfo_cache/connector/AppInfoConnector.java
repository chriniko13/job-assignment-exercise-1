package com.job.assignment.appinfo_cache.connector;

import com.job.assignment.appinfo_cache.dto.AppInfoDto;

public interface AppInfoConnector {

    AppInfoDto get(String name);
}
