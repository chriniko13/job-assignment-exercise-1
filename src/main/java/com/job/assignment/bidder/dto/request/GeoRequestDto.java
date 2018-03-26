package com.job.assignment.bidder.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeoRequestDto {

    private String country;
    private long lat;
    private long lon;

}
