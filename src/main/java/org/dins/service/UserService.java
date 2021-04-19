package org.dins.service;

import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;

import java.util.Collection;

public interface UserService {

    /**
     * Returns list of all users without phones
     * @return list of users
     */
    Collection<UserWithoutPhoneBookDto> getAllUsers();

    /**
     * Finds user by passed ID
     *
     * @param userId the indentifier of user to find
     * @return user with phones
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    UserDto getUser(Integer userId);

    //
    /**/

    UserDto createUser(UserDto userDto);

    Boolean deleteUser(Integer userId);

    UserDto editUser(Integer userId, UserDto dto);

    Collection<UserWithoutPhoneBookDto> findByName(String name);

}
