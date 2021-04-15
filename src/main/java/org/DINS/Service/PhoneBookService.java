package org.DINS.Service;

import org.DINS.model.dto.NumberInPhoneBookDto;
import org.DINS.model.dto.PhoneBooksDto;

import java.util.List;

public interface PhoneBookService {

    public List<PhoneBooksDto> getAllPhoneNumbers();
    public PhoneBooksDto getPhoneNumber(Integer userId);
    public void createPhoneNumber(Integer userId, NumberInPhoneBookDto phoneNumber);
    public Boolean deletePhone(Integer userId);
    public void editPhone(Integer userId);


}
