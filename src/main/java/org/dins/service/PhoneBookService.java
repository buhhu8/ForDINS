package org.dins.service;

import org.dins.model.dto.PhoneBookRecordDto;

import java.util.Collection;

public interface PhoneBookService {

     /**
      * Return list of all phone numbers for user
      *
      * @param userId the indentifier of user to find
      * @return list of phone numbers
      * @throws org.dins.exception.UserNotFoundException if no user with such ID
      */
     Collection<PhoneBookRecordDto> getAllPhoneNumbers(Integer userId);

     /**
      * Return phone number for user
      *
      * @param userId the indentifier of user to find
      * @param numberId the indentifier of phone to find
      * @return phone book record for user
      * @throws org.dins.exception.UserNotFoundException if no user with such ID
      */
     PhoneBookRecordDto getPhoneNumber(Integer userId, Integer numberId);

     /**
      * Create new phone book record for user
      *
      * @param userId the indentifier of user whom add new record
      * @param phoneNumber the indentifier of phone book record to create
      * @throws org.dins.exception.UserNotFoundException if no user with such ID
      */

     void createPhoneNumber(Integer userId, PhoneBookRecordDto phoneNumber);

     /**
      * Delete phone book record
      *
      * @param userId the indentifier of user from where phone book record delete
      * @param numberId the indentifier of phone book record to delete
      * @return true if record deleted
      */
     Boolean deletePhone(Integer userId, Integer numberId);

     /**
      * Edit phone book record for user
      *
      * @param userId the indentifier of user from where phone book record edit
      * @param phoneNumber the indentifier of phone book record which edit
      * @param dto the indentifier of phone book record to edit
      * @throws org.dins.exception.UserNotFoundException if no user with such ID
      * @throws org.dins.exception.NumberNotFoundException if no number with such ID
      */
     void editPhone(Integer userId, Integer phoneNumber, PhoneBookRecordDto dto);

     /**
      * Find phone number record by number or part of number
      *
      * @param userId the indentifier of user where phone book record find
      * @param number the indentifier of phone number to find
      * @return list of all found phone number records
      * @throws org.dins.exception.UserNotFoundException if no user with such ID
      */
     Collection<PhoneBookRecordDto> findByNumber(Integer userId, String number);


}
