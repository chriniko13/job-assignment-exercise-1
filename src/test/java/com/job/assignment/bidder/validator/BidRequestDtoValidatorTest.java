package com.job.assignment.bidder.validator;

import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.exception.ServiceValidationException;
import com.job.assignment.util.DataGenerator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BidRequestDtoValidatorTest implements DataGenerator {

    private BidRequestDtoValidator bidRequestDtoValidator;

    // create a rule for an exception grabber that you can use across
    // the methods in this test class
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        bidRequestDtoValidator = new BidRequestDtoValidator();
    }

    @Test
    public void validate() throws Exception {

        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();

        //when-then
        bidRequestDtoValidator.validate(bidRequestDto);

    }

    @Test
    public void validate_bid_request_id_null() throws Exception {

        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.setId(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);

    }

    @Test
    public void validate_bid_request_id_empty() throws Exception {

        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.setId("");

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);

    }

    @Test
    public void validate_device_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.setDevice(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().setGeo(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_country_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setCountry(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lat_invalid_1() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLat(-91L);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lat_invalid_2() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLat(91L);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lat_cornercase_1() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLat(90L);

        //when-then
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lat_cornercase_2() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLat(-90L);

        //when-then
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lon_invalid_1() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLon(-181L);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lon_cornercase_1() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLon(180L);

        //when
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lon_cornercase_2() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLon(-180L);

        //when
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_geo_lon_invalid_3() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().getGeo().setLon(181L);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_os_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().setOs(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_device_os_not_supported() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getDevice().setOs("BlackBerry");

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_app_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.setApp(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_app_id_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getApp().setId(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_app_id_empty() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getApp().setId("");

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_app_name_null() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getApp().setName(null);

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }

    @Test
    public void validate_app_name_empty() throws Exception {
        //given
        BidRequestDto bidRequestDto = generateBidRequestDto();
        bidRequestDto.getApp().setName("");

        //when-then
        exceptionGrabber.expect(ServiceValidationException.class);
        bidRequestDtoValidator.validate(bidRequestDto);
    }
}