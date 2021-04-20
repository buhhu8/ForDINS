package org.dins.service;

import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;

import java.util.Collection;

public interface UserService {

    /**
     * Return list of all users without phones
     * @return list of users
     */
    Collection<UserWithoutPhoneBookDto> getAllUsers();

    /**
     * Find user by passed ID
     *
     * @param userId the indentifier of user to find
     * @return user with phones
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    UserDto getUser(Integer userId);

    /**
     * Create new user
     *
     * @param userDto send via JSON userName
     * @return created DTO with userId and UserName
     */

    UserDto createUser(UserDto userDto);

    /**
     * Delete user by ID
     *
     * @param userId the indentifier of user to delete
     * @return true if deleted success
     */
    Boolean deleteUser(Integer userId);

    /**
     * Edit user by passed userId
     *
     * @param userId the indentifier of user to edit
     * @param dto the UserDto which replace our user
     * @return edited DTO
     */

    UserDto editUser(Integer userId, UserDto dto);

    /**
     * Find users record by name or part of name
     *
     * @param name the indentifier of user to find
     * @return list of all found users
     */

    Collection<UserWithoutPhoneBookDto> findByName(String name);

}
