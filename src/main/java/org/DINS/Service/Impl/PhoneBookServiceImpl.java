package org.DINS.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.PhoneBookService;
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

        Map<Integer, PhoneBooksDto> phoneBooksDtoMap = userService.getUser(userId).getPhoneBooksDtoMap();
        Iterator<Map.Entry<Integer,PhoneBooksDto>> iterator = phoneBooksDtoMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, PhoneBooksDto> pair = iterator.next();
            System.out.println(pair.getKey() + pair.getValue());
        }


        return phoneBooksDtoMap;

    }

    @Override
    public PhoneBooksDto getPhoneNumber(Integer userId, Integer numberId) {

        UsersDto user = userService.getUser(userId);
        return user.getPhoneBooksDtoMap().get(numberId);

    }

    @Override
    public void createPhoneNumber(Integer userId, PhoneBooksDto number) {

        Integer id = userService.getUser(userId).getPhoneBooksDtoMap().size();
        id= id + 1;
        number.setRecordId(id);
        numberInPhoneBookDtoMap.put(id,number);
        userService.getUser(userId).addIntoUsers(id,numberInPhoneBookDtoMap.get(id));

    }

    @Override
    public Boolean deletePhone(Integer userId, Integer numderId) {
       PhoneBooksDto user =  getPhoneNumber(userId,numderId);
       if(user!=null){
            userService.getUser(userId).getPhoneBooksDtoMap().remove(numderId);
            return true;
        }
        return false;
    }

    @Override
    public void editPhone(Integer userId) {

    }
}
