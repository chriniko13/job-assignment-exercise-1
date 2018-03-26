package com.job.assignment.bidder.gateway;

import com.job.assignment.bidder.dto.internal.BidResponseTransformerResultDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;

public interface BidGateway {

    BidResponseTransformerResultDto bid(BidRequestDto bidRequestDto);

}
