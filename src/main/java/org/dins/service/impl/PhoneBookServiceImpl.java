package org.dins.service.impl;

import lombok.RequiredArgsConstructor;
import org.dins.exception.NumberNotFoundException;
import org.dins.exception.UserNotFoundException;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.model.dto.UserDto;
import org.dins.service.PhoneBookService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneBookServiceImpl implements PhoneBookService {

    private Map<Integer, PhoneBookRecordDto> numberInPhoneBookDtoMap = new HashMap<>();
    private final UserServiceImpl userService;

    @Override
    public Collection<PhoneBookRecordDto> getAllPhoneNumbers(Integer userId) {
        UserDto user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user.getPhoneBook().entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

    }

    @Override
    public PhoneBookRecordDto getPhoneNumber(Integer userId, Integer numberId) {
        UserDto user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        // PhoneBookRecordDto numberDto = user.getPhoneBook().get(numberId);
        // if () { throw }
        // return numberDto;

        if(user.getPhoneBook().get(numberId) == null) {
            throw new NumberNotFoundException(numberId);
        }
        return user.getPhoneBook().get(numberId);
    }

    @Override
    public void createPhoneNumber(Integer userId, PhoneBookRecordDto phoneBookRecordDto) {
        UserDto user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        Integer id = user.getPhoneBook().size();
        id = id + 1;
        phoneBookRecordDto.setRecordId(id);
        numberInPhoneBookDtoMap.put(id, phoneBookRecordDto);
        user.addPhone(id, numberInPhoneBookDtoMap.get(id));
    }

    @Override
    public Boolean deletePhone(Integer userId, Integer numderId) {
        PhoneBookRecordDto user = getPhoneNumber(userId, numderId);
        if (user == null) {
            throw new NumberNotFoundException(numderId);
        }
        userService.getUser(userId).getPhoneBook().remove(numderId);
        return true;

    }

    @Override
    public void editPhone(Integer userId, Integer numberId, PhoneBookRecordDto dto) {
        UserDto user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        if(user.getPhoneBook().get(numberId)== null){
            throw new NumberNotFoundException(numberId);
        }
        dto.setRecordId(numberId);
        numberInPhoneBookDtoMap.put(numberId, dto);

        user.addPhone(numberId, numberInPhoneBookDtoMap.get(numberId));
    }

    @Override
    public Collection<PhoneBookRecordDto> findByNumber(Integer userId, String number) {
        UserDto user = userService.getUser(userId);
        if(user== null){
            throw new UserNotFoundException(userId);
        }
        return user.getPhoneBook().entrySet().stream()
                .filter(entry -> entry.getValue().getPhoneNumber().toLowerCase().contains(number.toLowerCase()))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());


    }


}