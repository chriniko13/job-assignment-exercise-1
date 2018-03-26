package com.job.assignment.util;

import com.job.assignment.bidder.dto.internal.SupportedOs;
import com.job.assignment.bidder.dto.response.BidInfoResponseDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import com.job.assignment.campaign.dto.response.CampaignResponseDto;
import com.job.assignment.bidder.dto.request.AppRequestDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.dto.request.DeviceRequestDto;
import com.job.assignment.bidder.dto.request.GeoRequestDto;

import java.math.BigDecimal;
import java.util.*;

public interface DataGenerator {

    default CampaignResponseDto generateCampaignResponseDto() {

        return new CampaignResponseDto("5a3dce46",
                "Test Campaign 1",
                BigDecimal.valueOf(1.23D),
                "<a href=\"http://example.com/click/qbFCjzXR9rkf8qa4\"><img src=\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\" height=\"250\" width=\"300\" alt=\"\"/></a><img src=\"http://example.com/win/qbFCjzXR9rkf8qa4\" height=\"1\" width=\"1\" alt=\"\"/>\r\n",
                Arrays.asList("USA", "GBR", "GRC"));

    }

    default List<CampaignResponseDto> generateCampaignResponseDtos() {


        return Arrays.asList(
                new CampaignResponseDto("5a3dce46",
                        "Test Campaign 1",
                        BigDecimal.valueOf(1.23D),
                        "<a href=\"http://example.com/click/qbFCjzXR9rkf8qa4\"><img src=\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\" height=\"250\" width=\"300\" alt=\"\"/></a><img src=\"http://example.com/win/qbFCjzXR9rkf8qa4\" height=\"1\" width=\"1\" alt=\"\"/>\r\n",
                        Arrays.asList("USA", "GBR", "GRC")),

                new CampaignResponseDto("c56bc77b",
                        "Test Campaign 2",
                        BigDecimal.valueOf(0.45D),
                        "<a href=\"http://example.com/click/qbFCjzXR9rkf8qa4\"><img src=\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\" height=\"250\" width=\"300\" alt=\"\"/></a><img src=\"http://example.com/win/qbFCjzXR9rkf8qa4\" height=\"1\" width=\"1\" alt=\"\"/>\r\n",
                        Arrays.asList("BRA", "EGY")),

                new CampaignResponseDto("a20579a5",
                        "Test Campaign 3",
                        BigDecimal.valueOf(2.21D),
                        "<a href=\"http://example.com/click/qbFCjzXR9rkf8qa4\"><img src=\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\" height=\"250\" width=\"300\" alt=\"\"/></a><img src=\"http://example.com/win/qbFCjzXR9rkf8qa4\" height=\"1\" width=\"1\" alt=\"\"/>\r\n",
                        Arrays.asList("HUN", "MEX")),

                new CampaignResponseDto("e919799e",
                        "Test Campaign 4",
                        BigDecimal.valueOf(0.39D),
                        "<a href=\"http://example.com/click/qbFCjzXR9rkf8qa4\"><img src=\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\" height=\"250\" width=\"300\" alt=\"\"/></a><img src=\"http://example.com/win/qbFCjzXR9rkf8qa4\" height=\"1\" width=\"1\" alt=\"\"/>\r\n",
                        Collections.singletonList("USA")),

                new CampaignResponseDto("ef88888f",
                        "Test Campaign 5",
                        BigDecimal.valueOf(1.6D),
                        "<a href=\"http://example.com/click/qbFCjzXR9rkf8qa4\"><img src=\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\" height=\"250\" width=\"300\" alt=\"\"/></a><img src=\"http://example.com/win/qbFCjzXR9rkf8qa4\" height=\"1\" width=\"1\" alt=\"\"/>\r\n",
                        Collections.singletonList("GBR"))


        );

    }

    default CampaignResponseDto generateCampaignResponseDto(int idx) {

        List<CampaignResponseDto> dtos = generateCampaignResponseDtos();

        if (idx < 1 || idx > dtos.size()) {
            throw new IllegalArgumentException("choose one from [1-5]");
        }

        return generateCampaignResponseDtos().get(idx - 1);
    }

    default BidRequestDto generateBidRequestDto() {

        return new BidRequestDto("e7fe51ce4f6376876353ff0961c2cb0d",
                new AppRequestDto("e7fe51ce-4f63-7687-6353-ff0961c2cb0d", "Morecast Weather"),
                new DeviceRequestDto(SupportedOs.Android.name(), new GeoRequestDto("USA", 0, 0)));

    }

    default BidResponseDto generateBidResponseDto() {

        return new BidResponseDto("e7fe51ce4f6376876353ff0961c2cb0d",
                new BidInfoResponseDto("5a3dce46", BigDecimal.valueOf(1.23D),
                        "<a href=\\\"http://example.com/click/qbFCjzXR9rkf8qa4\\\"><img src=\\\"http://assets.example.com/ad_assets/files/000/000/002/original/banner_300_250.png\\\" height=\\\"250\\\" width=\\\"300\\\" alt=\\\"\\\"/></a><img src=\\\"http://example.com/win/qbFCjzXR9rkf8qa4\\\" height=\\\"1\\\" width=\\\"1\\\" alt=\\\"\\\"/>\\r\\n"));
    }

    default Map<String, Object> generateDummyHeaders() {
        Map<String, Object> dummyHeaders = new HashMap<>();
        dummyHeaders.put("dummy-key", "dummy-value");
        return dummyHeaders;
    }
}
