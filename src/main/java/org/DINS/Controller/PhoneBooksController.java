package org.DINS.Controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import lombok.RequiredArgsConstructor;
import org.DINS.Service.Impl.PhoneBookServiceImpl;
import org.DINS.model.dto.PhoneBooksDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PhoneBooksController {

    private final PhoneBookServiceImpl phoneBookService;

    @PostMapping("/{userId}/phone")
    public void createPhone(@PathVariable Integer userId, @RequestBody PhoneBooksDto number){
        phoneBookService.createPhoneNumber(userId,number);

    }

    @GetMapping("/{userId}/phones")
    public ResponseEntity<?> getAllPhonesByUserId(@PathVariable Integer userId){
        Map<Integer, PhoneBooksDto> allPhoneNumbers = phoneBookService.getAllPhoneNumbers(userId);
        if(allPhoneNumbers!=null){
            return new ResponseEntity<>(allPhoneNumbers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userId}/{numberId}")
    public ResponseEntity<?> getPhoneById(@PathVariable Integer userId, @PathVariable Integer numberId){
        PhoneBooksDto phoneNumber = phoneBookService.getPhoneNumber(userId, numberId);
        if(phoneNumber!=null){
            return new ResponseEntity<>(phoneNumber,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}/{numberId}")
    public ResponseEntity<?> deletePhoneById(@PathVariable Integer userId, @PathVariable Integer numberId){
        if(phoneBookService.deletePhone(userId,numberId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
;