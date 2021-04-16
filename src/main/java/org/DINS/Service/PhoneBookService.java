package org.DINS.Service;

import org.DINS.model.dto.PhoneBooksDto;

import java.util.List;
import java.util.Map;

public interface PhoneBookService {

    public Map<Integer, PhoneBooksDto> getAllPhoneNumbers(Integer userId);
    public PhoneBooksDto getPhoneNumber(Integer userId, Integer numberId);
    public void createPhoneNumber(Integer userId, PhoneBooksDto phoneNumber);
    public Boolean deletePhone(Integer userId, Integer numberId);
    public void editPhone(Integer userId);


}
