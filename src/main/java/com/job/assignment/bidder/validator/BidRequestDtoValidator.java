package com.job.assignment.bidder.validator;

import com.job.assignment.bidder.dto.internal.SupportedOs;
import com.job.assignment.bidder.dto.request.AppRequestDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.dto.request.DeviceRequestDto;
import com.job.assignment.bidder.dto.request.GeoRequestDto;
import com.job.assignment.bidder.exception.ServiceValidationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BidRequestDtoValidator implements Validator<BidRequestDto> {

    @Override
    public void validate(BidRequestDto bidRequestDto) {
        validateId(bidRequestDto);
        validateDevice(bidRequestDto);
        validateApp(bidRequestDto);
    }

    private void validateId(BidRequestDto bidRequestDto) {
        if (bidRequestDto.getId() == null || bidRequestDto.getId().isEmpty()) {
            throw new ServiceValidationException("Missing id", "Please provide id!");
        }
    }

    private void validateDevice(BidRequestDto bidRequestDto) {
        if (bidRequestDto.getDevice() == null) {
            throw new ServiceValidationException("Missing device", "Please provide device info!");
        }

        validateDeviceGeo(bidRequestDto);
        validateDeviceOs(bidRequestDto);
    }

    private void validateDeviceGeo(BidRequestDto bidRequestDto) {
        DeviceRequestDto device = bidRequestDto.getDevice();
        if (device.getGeo() == null) {
            throw new ServiceValidationException("Missing device.geo", "Please provide device.geo info!");
        }

        GeoRequestDto geo = device.getGeo();
        if (geo.getCountry() == null) {
            throw new ServiceValidationException("Missing device.geo.country", "Please provide device.geo.country info!");
        }

        if (geo.getLat() < -90 || geo.getLat() > 90) {
            throw new ServiceValidationException("Incorrect device.geo.lat", "Please provide a correct[-90,90] device.geo.lat info!");
        }

        if (geo.getLon() < -180 || geo.getLon() > 180) {
            throw new ServiceValidationException("Incorrect device.geo.lon", "Please provide a correct[-180,180] device.geo.lon info!");
        }
    }

    private void validateDeviceOs(BidRequestDto bidRequestDto) {

        DeviceRequestDto device = bidRequestDto.getDevice();

        if (device.getOs() == null || device.getOs().isEmpty()) {
            throw new ServiceValidationException("Missing device.os", "Please provide device.os info!");
        }

        String os = device.getOs();
        SupportedOs[] supportedOs = SupportedOs.values();
        if (Arrays.stream(supportedOs).noneMatch(val -> val.name().equalsIgnoreCase(os))) {
            throw new ServiceValidationException("Not valid device.os", "Please provide a correct "
                    + Arrays.toString(supportedOs)
                    + " device.os!");
        }

    }

    private void validateApp(BidRequestDto bidRequestDto) {
        if (bidRequestDto.getApp() == null) {
            throw new ServiceValidationException("Missing app", "Please provide app info!");
        }

        AppRequestDto app = bidRequestDto.getApp();

        if (app.getId() == null || app.getId().isEmpty()) {
            throw new ServiceValidationException("Missing app.id", "Please provide app.idd info!");
        }

        if (app.getName() == null || app.getName().isEmpty()) {
            throw new ServiceValidationException("Missing app.name", "Please provide app.name info!");
        }
    }
}
