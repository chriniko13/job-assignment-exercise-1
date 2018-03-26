package com.job.assignment.bidder.dto.internal;

import com.job.assignment.bidder.dto.response.BidResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidResponseTransformerResultDto {

    private boolean result;
    private BidResponseDto bidResponseDto;

    public BidResponseTransformerResultDto(boolean result) {
        this.result = result;
    }
}
