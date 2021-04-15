package org.DINS.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.PhoneBookService;
import org.DINS.model.dto.NumberInPhoneBookDto;
import org.DINS.model.dto.PhoneBooksDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PhoneBookServiceImpl implements PhoneBookService {

    private Map<Integer,PhoneBooksDto> phoneBooksDtoMap = new HashMap<>();
    private Map<Integer,NumberInPhoneBookDto> numberInPhoneBookDtoMap = new HashMap<>();
    private AtomicInteger phoneNumberId = new AtomicInteger();
    private final UserServiceImpl userService;


    @Override
    public List<PhoneBooksDto> getAllPhoneNumbers() {
        return null;
    }

    @Override
    public PhoneBooksDto getPhoneNumber(Integer userId) {
        return null;
    }

    @Override
    public void createPhoneNumber(Integer userId, NumberInPhoneBookDto number) {
        Integer id = phoneNumberId.incrementAndGet();
        number.setRecordId(id);
        PhoneBooksDto dto = new PhoneBooksDto();
        dto.setPhoneBookId(userId);
        dto.addIntoMap(userId,number);
        numberInPhoneBookDtoMap.put(id,number);
        System.out.println(numberInPhoneBookDtoMap);

        phoneBooksDtoMap.put(userId,dto);
        userService.getUser(userId).addIntoUsers(userId,phoneBooksDtoMap.get(userId));


    }


    @Override
    public Boolean deletePhone(Integer userId) {
        return null;
    }

    @Override
    public void editPhone(Integer userId) {

    }
}
