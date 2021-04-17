package org.DINS.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.PhoneBookService;
import org.DINS.findMethod.FindByNumber;
import org.DINS.model.dto.PhoneBooksDto;
import org.DINS.model.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PhoneBookServiceImpl implements PhoneBookService {

    private Map<Integer, PhoneBooksDto> numberInPhoneBookDtoMap = new HashMap<>();
    private final UserServiceImpl userService;

    @Override
    public Map<Integer, PhoneBooksDto> getAllPhoneNumbers(Integer userId) {
        if (userService.getUser(userId) != null) {
            Map<Integer, PhoneBooksDto> phoneBooksDtoMap = userService.getUser(userId).getPhoneBooksDtoMap();
            return phoneBooksDtoMap;
        }
        return new HashMap<>();

    }

    @Override
    public PhoneBooksDto getPhoneNumber(Integer userId, Integer numberId) {
        if (userService.getUser(userId) != null) {
            UsersDto user = userService.getUser(userId);
            return user.getPhoneBooksDtoMap().get(numberId);

        }

        return new PhoneBooksDto();
    }

    @Override
    public Boolean createPhoneNumber(Integer userId, PhoneBooksDto phoneBooksDto) {
        if (userService.getUser(userId) != null) {
            Integer id = userService.getUser(userId).getPhoneBooksDtoMap().size();
            id = id + 1;
            phoneBooksDto.setRecordId(id);
            numberInPhoneBookDtoMap.put(id, phoneBooksDto);
            userService.getUser(userId).addIntoUsers(id, numberInPhoneBookDtoMap.get(id));
            return true;
        }
        return false;

    }

    @Override
    public Boolean deletePhone(Integer userId, Integer numderId) {
        PhoneBooksDto user = getPhoneNumber(userId, numderId);
        try {
            user.getPhoneNumber();
            userService.getUser(userId).getPhoneBooksDtoMap().remove(numderId);
            return true;
        } catch (NullPointerException exc) {
            return false;
        }
    }

    @Override
    public Boolean editPhone(Integer userId, Integer numberId, PhoneBooksDto dto) {
        try {
            if (userService.getUser(userId).getPhoneBooksDtoMap().get(numberId) != null) {
                dto.setRecordId(numberId);
                numberInPhoneBookDtoMap.put(numberId, dto);
                userService.getUser(userId).addIntoUsers(numberId, numberInPhoneBookDtoMap.get(numberId));
                return true;
            }
            return false;
        } catch (NullPointerException exc) {
            return false;
        }
    }

    @Override
    public Map<Integer, String> findByNumber(String number) {
        FindByNumber findByNumber = new FindByNumber();
        Map<Integer, String> resultMap = new HashMap<>();
        String foundNumber;
        for (Map.Entry<Integer, PhoneBooksDto> entry : numberInPhoneBookDtoMap.entrySet()) {
            foundNumber = entry.getValue().getPhoneNumber();
            if (findByNumber.getNumberFirstEntry(foundNumber, number) != -1) {
                resultMap.put(entry.getKey(), entry.getValue().getPhoneNumber());
            }
        }
        return resultMap;
    }
}