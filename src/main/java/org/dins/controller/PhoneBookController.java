package org.dins.controller;

import lombok.RequiredArgsConstructor;
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
public class PhoneBookController {

    private final PhoneBookServiceImpl phoneBookService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createPhone(@PathVariable Integer userId, @RequestBody @Valid PhoneBookRecordDto number) {

        phoneBookService.createPhoneNumber(userId, number);
        return new ResponseEntity<>(HttpStatus.OK);
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
