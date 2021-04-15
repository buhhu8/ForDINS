package org.DINS.Controller;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.Impl.PhoneBookServiceImpl;
import org.DINS.Service.PhoneBookService;
import org.DINS.model.dto.NumberInPhoneBookDto;
import org.DINS.model.dto.PhoneBooksDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PhoneBooksController {

    private final PhoneBookServiceImpl phoneBookService;

    @PostMapping("/{userId}/phone")
    public void createPhone(@PathVariable Integer userId, @RequestBody NumberInPhoneBookDto number){
        phoneBookService.createPhoneNumber(userId,number);


    }

}
