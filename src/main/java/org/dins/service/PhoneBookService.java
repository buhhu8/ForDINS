package org.dins.service;

import org.dins.model.dto.PhoneBookRecordDto;

import java.util.Collection;

public interface PhoneBookService {

     Collection<PhoneBookRecordDto> getAllPhoneNumbers(Integer userId);
     PhoneBookRecordDto getPhoneNumber(Integer userId, Integer numberId);
     void createPhoneNumber(Integer userId, PhoneBookRecordDto phoneNumber);
     Boolean deletePhone(Integer userId, Integer numberId);
     void editPhone(Integer userId, Integer phoneNumber, PhoneBookRecordDto dto);
     Collection<PhoneBookRecordDto> findByNumber(Integer userId, String number);


}
