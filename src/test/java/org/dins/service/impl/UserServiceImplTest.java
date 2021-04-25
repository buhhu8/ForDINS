package org.dins.service.impl;

import org.dins.exception.UserNotFoundException;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private final UserServiceImpl userService = new UserServiceImpl();


    @Test
    @DisplayName("getAllUsers: Should return empty list if there are no users")
    public void testGetAllUsers_usersDoNotExist_returnEmptyList() {
        Collection<UserWithoutPhoneBookDto> result = userService.getAllUsers();
        assertEquals(result, new ArrayList<>());
    }

    @Test
    @DisplayName("getAllUsers: Should return list with all users")
    public void testGetAllUsers_usersExist_returnUsersList() {
        userService.createUser(initializUser());
        assertNotNull(userService.getAllUsers());
    }

    @Test
    @DisplayName("getUser: Should throw exception if no user with passed ID")
    public void testGetUser_userDoesNotExist_returnNull() {
        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUser(1));
        assertEquals("User with such ID 1 doesn't exist", exception.getMessage());
    }

    @Test
    @DisplayName("getUser: Should return userDto if user exists with passed ID")
    public void testGetUser_userExists_returnUser() {
        userService.createUser(initializUser());
        UserDto dto = userService.getUser(1);
        assertNotNull(dto);
    }

    @Test
    @DisplayName("createUser: Should return new userDto")
    public void testCreateUser_userValid_returnUserDto() {
        assertNotNull(userService.createUser(initializUser()));
    }

    @Test
    @DisplayName("deleteUser: Should return true if user was deleted")
    public void testDeleteUser_userExists_returnTrue() {

        userService.createUser(initializUser());
        assertTrue(userService.deleteUser(1));

    }

    @Test
    @DisplayName("deleteUser: Should return false if user was not found")
    public void testDeleteUser_userDoesNotExist_returnFalse() {

        assertFalse(userService.deleteUser(1));

    }

    @Test
    @DisplayName("editUser: Should return new UserDto")
    public void testEditUser_userExists_returnNewUserDto() {

        UserDto user = userService.createUser(initializUser());
        UserDto dto = new UserDto();
        dto.setUserName("Anton");
        UserDto result = userService.editUser(1, dto);
        assertEquals(result, user);

    }

    @Test
    @DisplayName("editUser: Should throw exception if user does not exist ")
    public void testEditUser_userDoesNotExist_returnException() {

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.editUser(1, new UserDto()));
        assertEquals("User with such ID 1 doesn't exist", exception.getMessage());

    }

    @Test
    @DisplayName("findByName: Should return all users which contains part of name")
    public void testFindByName_UserExists_returnName() {
        UserWithoutPhoneBookDto dto = new UserWithoutPhoneBookDto(1, "Denis");
        Collection<UserWithoutPhoneBookDto> expectedResult = new ArrayList<>();
        expectedResult.add(dto);
        userService.createUser(initializUser());
        Collection<UserWithoutPhoneBookDto> result = userService.findByName("Den");
        assertEquals(expectedResult, result);

    }


    public UserDto initializUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("Denis");
        userDto.addPhone(1, new PhoneBookRecordDto());
        return userDto;
    }
}