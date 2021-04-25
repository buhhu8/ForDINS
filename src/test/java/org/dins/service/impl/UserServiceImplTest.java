package org.dins.service.impl;

import org.dins.exception.UserNotFoundException;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceImplTest {

    private final UserServiceImpl userService = new UserServiceImpl();

    @Test
    @DisplayName("getAllUsers: Should return empty list if there are no users")
    public void testGetAllUsers_usersDoNotExist_returnEmptyList() {
        Collection<UserWithoutPhoneBookDto> result = userService.getAllUsers();
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("getAllUsers: Should return list with all users")
    public void testGetAllUsers_usersExist_returnUsersList() {
        UserDto createdUser = userService.createUser(initializeUser());

        Collection<UserWithoutPhoneBookDto> result = userService.getAllUsers();
        assertFalse(result.isEmpty());

        boolean userExists = result.stream()
                .anyMatch(user -> user.getUserName().equals(createdUser.getUserName()));
        assertTrue(userExists);
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
        UserDto user = userService.createUser(initializeUser());
        UserDto result = userService.getUser(1);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("createUser: Should return new userDto")
    public void testCreateUser_userValid_returnUserDto() {
        UserDto result = userService.createUser(initializeUser());
        assertNotNull(result.getUserId());
    }

    @Test
    @DisplayName("deleteUser: Should return true if user was deleted")
    public void testDeleteUser_userExists_returnTrue() {
        userService.createUser(initializeUser());
        assertTrue(userService.deleteUser(1));

        assertThrows(UserNotFoundException.class, () -> userService.getUser(1));
    }

    @Test
    @DisplayName("deleteUser: Should return false if user was not found")
    public void testDeleteUser_userDoesNotExist_returnFalse() {
        assertFalse(userService.deleteUser(1));
    }

    @Test
    @DisplayName("editUser: Should return new UserDto")
    public void testEditUser_userExists_returnNewUserDto() {
        UserDto user = userService.createUser(initializeUser());
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
        Collection<UserWithoutPhoneBookDto> expectedResult = Collections.singletonList(dto);

        userService.createUser(initializeUser());

        Collection<UserWithoutPhoneBookDto> result = userService.findByName("Den");
        assertEquals(expectedResult, result);
    }


    public UserDto initializeUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("Denis");
        userDto.addPhone(1, new PhoneBookRecordDto());
        return userDto;
    }
}