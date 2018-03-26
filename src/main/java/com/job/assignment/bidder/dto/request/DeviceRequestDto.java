package com.job.assignment.bidder.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceRequestDto {

    private String os; // Note: not used enum here for a detailed validation message when os is not supported from our service.
    private GeoRequestDto geo;


}
