package com.job.assignment.bidder.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidRequestDto {

    private String id;
    private AppRequestDto app;
    private DeviceRequestDto device;

}
