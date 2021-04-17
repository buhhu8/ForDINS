package org.DINS.Service;


import org.DINS.model.dto.UsersDto;

import java.util.Map;

public interface UserService {

    public Map<Integer, String> getAllUsers();
    public UsersDto getUser(Integer userId);
    public UsersDto createUser(UsersDto usersDto);
    public Boolean deleteUser(Integer userId);
    public UsersDto editUser(Integer userId, UsersDto dto);
    public Map<Integer, String> findByName(String name);

}
