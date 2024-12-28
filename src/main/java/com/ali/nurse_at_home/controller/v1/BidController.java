package com.ali.nurse_at_home.controller.v1;

import com.ali.nurse_at_home.model.params.BidParams;
import com.ali.nurse_at_home.service.BidService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bids")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class BidController {

    BidService bidService;

    @PostMapping
    public ResponseEntity<?> createBid(@RequestBody @Valid BidParams params) {
        throw new ResponseStatusException(NOT_IMPLEMENTED);
//        return status(CREATED).body(bidService.create(params));
    }

    //принять/отклонить заявку (для медсестры)
    //отменить заявку (для пациента)
    //отметить что в пути по заявке (для медсестры)
    //отметить заявку как исполненную (для медсестры) + попросить юзера оставить отзыв о качестве исполнения
    //получить страницу заявок (для медсестры и пациента. завершенные, активные: принята или в пути)
}
