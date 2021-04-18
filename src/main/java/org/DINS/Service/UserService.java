package org.DINS.Service;

import org.DINS.model.dto.UserDto;
import org.DINS.model.dto.UserWithoutPhoneBookDto;

import java.util.Collection;
import java.util.Map;

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
     * @throws org.DINS.exception.UserNotFoundException if no user with such ID
     */
    UserDto getUser(Integer userId);

    //
    /**/

    UserDto createUser(UserDto userDto);

    Boolean deleteUser(Integer userId);

    UserDto editUser(Integer userId, UserDto dto);

    Collection<UserWithoutPhoneBookDto> findByName(String name);

}
