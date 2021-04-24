package org.dins.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.service.impl.PhoneBookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phones")
@Api(value = "PhoneBookController" , tags = {"Phone Book Controller"})

public class PhoneBookController {

    private final PhoneBookServiceImpl phoneBookService;


    @ApiOperation(value = "Record phone number in phone book")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK"),
            @ApiResponse(code =400, message = "\"phoneNumber\": \"должно соответствовать \\\"^[0-9]+$\\\"\" or \"phoneNumber\": \"Number must have 11 characters\"")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<?> createPhone(@PathVariable Integer userId, @RequestBody @Valid PhoneBookRecordDto number) {

        phoneBookService.createPhoneNumber(userId, number);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public Collection<PhoneBookRecordDto> getAllPhonesByUserId(@PathVariable Integer userId) {

        return phoneBookService.getAllPhoneNumbers(userId);
    }

    @GetMapping("/{userId}/{numberId}")
    public PhoneBookRecordDto getPhoneById(@PathVariable Integer userId, @PathVariable Integer numberId) {

        return phoneBookService.getPhoneNumber(userId, numberId);
    }

    @DeleteMapping("/{userId}/{numberId}")
    public ResponseEntity<?> deletePhoneById(@PathVariable Integer userId, @PathVariable Integer numberId) {

        if (phoneBookService.deletePhone(userId, numberId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{userId}/{numberId}")
    public ResponseEntity<?> editPhoneById(@PathVariable Integer userId,
                                           @PathVariable Integer numberId,
                                           @RequestBody @Valid PhoneBookRecordDto dto) {

        phoneBookService.editPhone(userId, numberId, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping({"/find/{userId}/{findNumber}"})
    public Collection<PhoneBookRecordDto> findByNumber(@PathVariable Integer userId, @PathVariable String findNumber) {

        return phoneBookService.findByNumber(userId, findNumber);
    }

}
