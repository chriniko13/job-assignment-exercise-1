package com.job.assignment.appinfo_cache.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.assignment.appinfo_cache.connector.AppInfoConnector;
import com.job.assignment.appinfo_cache.connector.AppInfoConnectorImpl;
import com.job.assignment.appinfo_cache.dto.AppInfoDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;

public class AppInfoCacheTest {

    private AppInfoCache appInfoCache;
    private AppInfoConnector appInfoConnector;

    @Before
    public void setUp() throws Exception {
        appInfoConnector = new AppInfoConnectorImpl(new ObjectMapper());
        appInfoCache = new AppInfoCacheImpl(appInfoConnector);
    }

    /*
        Note: We get result when an app info with the provided name exists in appinfo component.
     */
    @Test
    public void first_test()
            throws Exception {

        //when
        Optional<AppInfoDto> result = appInfoCache.get("App1");


        //then
        assertEquals(true, result.isPresent());
        assertNotNull(result);
        assertEquals("category1", result.get().getExtraInfo().getCategory());
        assertEquals("publisher1", result.get().getExtraInfo().getPublisher());

    }

    /*
        Note: we get no result when an app info with the provided name does not exist in appinfo component.
     */
    @Test
    public void second_test()
            throws Exception {

        //when
        Optional<AppInfoDto> result = appInfoCache.get("App1XYZ");

        //then
        assertEquals(false, result.isPresent());
    }

    /*
        Note: We get result when an app info with the provided name exists in appinfo component,
              and when we call it again (cache service) we do not call again the remote service (aka connector)

     */
    @Test
    public void third_test() throws Exception {

        //given
        AppInfoConnector mockedAppInfoConnector = Mockito.mock(AppInfoConnector.class);
        Mockito.when(mockedAppInfoConnector.get("AvoCarrotTest")).thenReturn(getDummyResponse());

        AppInfoCache appInfoCache = new AppInfoCacheImpl(mockedAppInfoConnector);

        //when
        int timesToInvoke = 5;
        IntStream.rangeClosed(1, timesToInvoke)
                .forEach(idx -> appInfoCache.get("AvoCarrotTest"));

        //then
        Mockito.verify(mockedAppInfoConnector, times(1)).get("AvoCarrotTest");
    }

    private AppInfoDto getDummyResponse() {
        return appInfoConnector.get("App2");
    }

}