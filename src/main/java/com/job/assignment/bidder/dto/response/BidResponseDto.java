package com.job.assignment.bidder.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidResponseDto {

    private String id;
    private BidInfoResponseDto bid;

}
