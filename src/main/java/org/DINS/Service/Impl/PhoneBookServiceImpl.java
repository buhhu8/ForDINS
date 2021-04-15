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
    PhoneBooksDto phoneBooksDto = new PhoneBooksDto();

    @Override
    public List<PhoneBooksDto> getAllPhoneNumbers() {
        return null;
    }

    @Override
    public PhoneBooksDto getPhoneNumber(Integer userId) {
        return null;
    }

    @Override
    public PhoneBooksDto createPhoneNumber(Integer userId, NumberInPhoneBookDto number) {
        Integer id = phoneNumberId.incrementAndGet();
        phoneBooksDto.setPhoneBookId(userId);
        numberInPhoneBookDtoMap.put(id,number);
        phoneBooksDto.setNumberInPhoneBookDtoMap(numberInPhoneBookDtoMap);
        userService.getUser(userId).setPhoneBooksDtoMap(phoneBooksDtoMap);
        phoneBooksDtoMap.put(userId,phoneBooksDto);
        return phoneBooksDto;
    }


    @Override
    public Boolean deletePhone(Integer userId) {
        return null;
    }

    @Override
    public void editPhone(Integer userId) {

    }
}
