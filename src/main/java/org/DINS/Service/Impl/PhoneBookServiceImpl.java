package org.DINS.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.PhoneBookService;
import org.DINS.findMethod.FindByNumber;
import org.DINS.model.dto.PhoneBookRecordDto;
import org.DINS.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PhoneBookServiceImpl implements PhoneBookService {

    private Map<Integer, PhoneBookRecordDto> numberInPhoneBookDtoMap = new HashMap<>();
    private final UserServiceImpl userService;

    @Override
    public Map<Integer, PhoneBookRecordDto> getAllPhoneNumbers(Integer userId) {
        if (userService.getUser(userId) != null) {
            Map<Integer, PhoneBookRecordDto> phoneBooksDtoMap = userService.getUser(userId).getPhoneBook();
            return phoneBooksDtoMap;
        }
        return new HashMap<>();

    }

    @Override
    public PhoneBookRecordDto getPhoneNumber(Integer userId, Integer numberId) {
        if (userService.getUser(userId) != null) {
            UserDto user = userService.getUser(userId);
            return user.getPhoneBook().get(numberId);

        }

        return new PhoneBookRecordDto();
    }

    @Override
    public Boolean createPhoneNumber(Integer userId, PhoneBookRecordDto phoneBookRecordDto) {
        if (userService.getUser(userId) != null) {
            Integer id = userService.getUser(userId).getPhoneBook().size();
            id = id + 1;
            phoneBookRecordDto.setRecordId(id);
            numberInPhoneBookDtoMap.put(id, phoneBookRecordDto);
            userService.getUser(userId).addPhone(id, numberInPhoneBookDtoMap.get(id));
            return true;
        }
        return false;

    }

    @Override
    public Boolean deletePhone(Integer userId, Integer numderId) {
        PhoneBookRecordDto user = getPhoneNumber(userId, numderId);
        try {
            user.getPhoneNumber();
            userService.getUser(userId).getPhoneBook().remove(numderId);
            return true;
        } catch (NullPointerException exc) {
            return false;
        }
    }

    @Override
    public Boolean editPhone(Integer userId, Integer numberId, PhoneBookRecordDto dto) {
        try {
            if (userService.getUser(userId).getPhoneBook().get(numberId) != null) {
                dto.setRecordId(numberId);
                numberInPhoneBookDtoMap.put(numberId, dto);
                userService.getUser(userId).addPhone(numberId, numberInPhoneBookDtoMap.get(numberId));
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
        for (Map.Entry<Integer, PhoneBookRecordDto> entry : numberInPhoneBookDtoMap.entrySet()) {
            foundNumber = entry.getValue().getPhoneNumber();
            if (findByNumber.getNumberFirstEntry(foundNumber, number) != -1) {
                resultMap.put(entry.getKey(), entry.getValue().getPhoneNumber());
            }
        }
        return resultMap;
    }
}