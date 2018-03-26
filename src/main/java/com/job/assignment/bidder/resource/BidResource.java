package com.job.assignment.bidder.resource;

import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import org.springframework.http.ResponseEntity;

public interface BidResource {

    ResponseEntity<BidResponseDto> bid(BidRequestDto bidRequestDto);
}
