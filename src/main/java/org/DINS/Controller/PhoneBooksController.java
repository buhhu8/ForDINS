package org.DINS.Controller;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.Impl.PhoneBookServiceImpl;
import org.DINS.model.dto.PhoneBookRecordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phones")
public class PhoneBooksController {

    private final PhoneBookServiceImpl phoneBookService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createPhone(@PathVariable Integer userId, @RequestBody @Valid PhoneBookRecordDto number){
        if(phoneBookService.createPhoneNumber(userId, number))
        {
             return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllPhonesByUserId(@PathVariable Integer userId){
        Map<Integer, PhoneBookRecordDto> allPhoneNumbers = phoneBookService.getAllPhoneNumbers(userId);
        if(allPhoneNumbers.get(1)!=null){
            return new ResponseEntity<>(allPhoneNumbers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/{numberId}")
    public ResponseEntity<?> getPhoneById(@PathVariable Integer userId, @PathVariable Integer numberId){
        PhoneBookRecordDto phoneNumber = phoneBookService.getPhoneNumber(userId, numberId);
        try{
            if(phoneNumber.getPhoneNumber()!=null)
            {
                return new ResponseEntity<>(phoneNumber,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (NullPointerException exc){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{userId}/{numberId}")
    public ResponseEntity<?> deletePhoneById(@PathVariable Integer userId, @PathVariable Integer numberId){
        if(phoneBookService.deletePhone(userId,numberId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{userId}/{numberId}")
    public ResponseEntity<?> editPhoneById(@PathVariable Integer userId,
                                           @PathVariable Integer numberId,
                                           @RequestBody @Valid PhoneBookRecordDto dto){

        if(phoneBookService.editPhone(userId, numberId, dto)){
            return new ResponseEntity<>(dto,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"/find-number/{findNumber}"})
    public ResponseEntity<?> findByNumber(@PathVariable String findNumber){
        return new ResponseEntity<>(phoneBookService.findByNumber(findNumber),HttpStatus.OK);
    }

}
