package com.job.assignment.bidder.resource;

import com.job.assignment.bidder.dto.internal.BidResponseTransformerResultDto;
import com.job.assignment.bidder.dto.request.BidRequestDto;
import com.job.assignment.bidder.dto.response.BidResponseDto;
import com.job.assignment.bidder.gateway.BidGateway;
import com.job.assignment.bidder.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bid")
public class BidResourceImpl implements BidResource {

    private final BidGateway bidGateway;
    private final Validator<BidRequestDto> bidRequestDtoValidator;

    @Autowired
    public BidResourceImpl(BidGateway bidGateway, Validator<BidRequestDto> bidRequestDtoValidator) {
        this.bidGateway = bidGateway;
        this.bidRequestDtoValidator = bidRequestDtoValidator;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<BidResponseDto> bid(@RequestBody BidRequestDto bidRequestDto) {

        bidRequestDtoValidator.validate(bidRequestDto);

        final BidResponseTransformerResultDto bidResponseTransformerResultDto = bidGateway.bid(bidRequestDto);

        return Optional
                .of(bidResponseTransformerResultDto)
                .filter(BidResponseTransformerResultDto::isResult)
                .map(BidResponseTransformerResultDto::getBidResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}
