package org.dins.service.impl;

import lombok.RequiredArgsConstructor;
import org.dins.exception.NumberNotFoundException;
import org.dins.exception.UserNotFoundException;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.model.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RequiredArgsConstructor
class PhoneBookServiceImplTest {

    @InjectMocks
    private PhoneBookServiceImpl phoneBookService;
    @Mock
    private UserServiceImpl userService;

    @Test
    @DisplayName("getAllPhoneNumbers: Should return list with all numbers")
    public void testGetAllPhoneNumbers_userExists_returnListWithUsers() {

        when(userService.getUser(1)).thenReturn(initialize());
        assertNotNull(phoneBookService.getAllPhoneNumbers(1));

    }

    @Test
    @DisplayName("getAllPhoneNumbers: Should throw exception if user does not exist")
    public void testGetAllPhoneNumbers_userDoesNotExist_phoneBookNull_returnException() {

        assertThrows(UserNotFoundException.class, () -> phoneBookService.getAllPhoneNumbers(1));

    }

    @Test
    @DisplayName("getPhoneNumber: Should return record in phone book for user")
    public void testGetPhoneNumber_userExistsPhoneExists_returnPhoneNumber() {

        PhoneBookRecordDto expectedResult = createPhoneBookDto();
        when(userService.getUser(1)).thenReturn(initialize());
        PhoneBookRecordDto result = phoneBookService.getPhoneNumber(1, 1);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("getPhoneNumber: Should throw UserNotFoundException")
    public void testGetPhoneNumber_userDoesNotExist_returnException() {

        assertThrows(UserNotFoundException.class, () -> phoneBookService.getPhoneNumber(1, 1));

    }

    @Test
    @DisplayName("getPhoneNumber: Should return NumberNotFoundException")
    public void testGetPhoneNumber_userExistsPhoneNumberDoesNotExist_returnException() {

        when(userService.getUser(1)).thenReturn(initialize());
        assertThrows(NumberNotFoundException.class, () -> phoneBookService.getPhoneNumber(1, 21));

    }

    @Test
    @DisplayName("createPhoneNumber: Should throw UserNotFoundException")
    public void testCreatePhoneNumber_userDoesNotExist_returnException() {

        assertThrows(UserNotFoundException.class, () -> phoneBookService.createPhoneNumber(22,new PhoneBookRecordDto()));

    }


    @Test
    @DisplayName("createPhoneNumber: Should create new phone")
    public void testCreatePhoneNumber_userDoesExists() {

        when(userService.getUser(1)).thenReturn(initialize());
        phoneBookService.createPhoneNumber(1, createPhoneBookDto());
        verify(userService,times(3)).getUser(1);

    }

    @Test
    @DisplayName("deletePhone: Should throw UserNotFoundException")
    public void testDeletePhone_userDoesNotExist_returnException() {

        assertThrows(UserNotFoundException.class, () -> phoneBookService.deletePhone(1, 221));

    }

    @Test
    @DisplayName("deletePhone: Should throw NumberNotFoundException")
    public void testDeletePhone_userExistsPhoneNumberDoesNotExist_returnException() {

        when(userService.getUser(1)).thenReturn(initialize());
        assertThrows(NumberNotFoundException.class, () -> phoneBookService.deletePhone(1, 221));

    }

    @Test
    @DisplayName("deletePhone: Should return true if user and number exists")
    public void testDeletePhone_UserExistsPhoneExists_returnTrue(){

        when(userService.getUser(1)).thenReturn(initialize());
        assertTrue(phoneBookService.deletePhone(1,1));

    }

    @Test
    @DisplayName("editPhone: Should throw UserNotFoundException")
    public void testEditPhone_userDoesNotExist_returnException() {

        assertThrows(UserNotFoundException.class, () -> phoneBookService.editPhone(1, 1,createPhoneBookDto()));

    }

    @Test
    @DisplayName("editPhone: Should throw NumberNotFoundException")
    public void testEditPhone_userExistsPhoneNumberDoesNotExist_returnException() {

        when(userService.getUser(1)).thenReturn(initialize());
        assertThrows(NumberNotFoundException.class, () -> phoneBookService.editPhone(1, 21, createPhoneBookDto()));

    }

    @Test
    @DisplayName("editPhone: Should edit phoneBook")
    public void testEditPhone_userExistsPhoneExists(){

        when(userService.getUser(1)).thenReturn(initialize());
        phoneBookService.editPhone(1,1,createPhoneBookDto());

        verify(userService,times(3)).getUser(1);

    }

    @Test
    @DisplayName("findByNumber: Should throw UserNotFoundException")
    public void testFindByNumber_userDoesNotExist_returnException() {

        assertThrows(UserNotFoundException.class, () -> phoneBookService.findByNumber(1, "234"));

    }


    @Test
    @DisplayName("findByNumber: Should return all phone numbers which contains part of number")
    public void testFindByNumber_userExists_returnAllFindNumber(){

        PhoneBookRecordDto dto = createPhoneBookDto();
        Collection<PhoneBookRecordDto> expectedResult = new ArrayList<>();
        expectedResult.add(dto);
        when(userService.getUser(1)).thenReturn(initialize());
        Collection<PhoneBookRecordDto> result = phoneBookService.findByNumber(1,"963");
        assertEquals(expectedResult,result);
    }


    public UserDto initialize() {
        UserDto dto = new UserDto();
        dto.setUserName("Denis");
        dto.addPhone(1, createPhoneBookDto());
        return dto;
    }

    public PhoneBookRecordDto createPhoneBookDto() {
        PhoneBookRecordDto dto = new PhoneBookRecordDto();
        dto.setRecordId(1);
        dto.setPhoneNumber("89633410783");
        dto.setFirstName("Denis");
        dto.setLastName("Dorofeev");
        return dto;
    }


}


