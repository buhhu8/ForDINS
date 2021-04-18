package org.DINS.Service;

import org.DINS.model.dto.PhoneBookRecordDto;

import java.util.Map;

public interface PhoneBookService {

    public Map<Integer, PhoneBookRecordDto> getAllPhoneNumbers(Integer userId);
    public PhoneBookRecordDto getPhoneNumber(Integer userId, Integer numberId);
    public Boolean createPhoneNumber(Integer userId, PhoneBookRecordDto phoneNumber);
    public Boolean deletePhone(Integer userId, Integer numberId);
    public Boolean editPhone(Integer userId, Integer phoneNumber, PhoneBookRecordDto dto);
    public Map<Integer,String> findByNumber(String number);


}
