package org.dins.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.dins.model.dto.ErrorMessageDto;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.service.impl.PhoneBookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<?> createPhone(@PathVariable Integer userId, @RequestBody @Valid PhoneBookRecordDto number) {

        phoneBookService.createPhoneNumber(userId, number);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    @ApiOperation(value = "Get all phone numbers for user by userId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    public Collection<PhoneBookRecordDto> getAllPhonesByUserId(@PathVariable Integer userId) {

        return phoneBookService.getAllPhoneNumbers(userId);
    }

    @GetMapping("/{userId}/{numberId}")
    @ApiOperation(value = "Get phone number for user by userId and numberId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    public PhoneBookRecordDto getPhoneById(@PathVariable Integer userId, @PathVariable Integer numberId) {

        return phoneBookService.getPhoneNumber(userId, numberId);
    }

    @DeleteMapping("/{userId}/{numberId}")
    @ApiOperation(value = "Delete phone number for user by userId and numberId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    public ResponseEntity<?> deletePhoneById(@PathVariable Integer userId, @PathVariable Integer numberId) {

        if (phoneBookService.deletePhone(userId, numberId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{userId}/{numberId}")
    @ApiOperation(value = "Edit phone number for user by userId and numberId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    public ResponseEntity<?> editPhoneById(@PathVariable Integer userId,
                                           @PathVariable Integer numberId,
                                           @RequestBody @Valid PhoneBookRecordDto dto) {

        phoneBookService.editPhone(userId, numberId, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping({"/search/{userId}"})
    @ApiOperation(value = "Find all phone number for user by userId and number or part of number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    public Collection<PhoneBookRecordDto> findByNumber(@PathVariable Integer userId, @RequestParam("findNumber")  String findNumber) {

        return phoneBookService.findByNumber(userId, findNumber);
    }

}
