package org.DINS.Service;


import org.DINS.model.dto.UsersDto;

import java.util.List;

public interface UserService {

    public List<UsersDto> getAllUsers();
    public UsersDto getUser(Integer userId);
    public UsersDto createUser(UsersDto usersDto);
    public Boolean deleteUser(Integer userId);
    public void editUser(Integer userId);

}
