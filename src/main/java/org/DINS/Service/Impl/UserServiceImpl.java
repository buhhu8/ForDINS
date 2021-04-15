package org.DINS.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.UserService;
import org.DINS.model.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AtomicInteger userId = new AtomicInteger();
    private final Map<Integer,UsersDto> usersMap = new HashMap<>();

    @Override
    public List<UsersDto> getAllUsers() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public UsersDto getUser(Integer id) {
       return usersMap.get(id);

    }

    @Override
    public UsersDto createUser(UsersDto usersDto) {
       final Integer id = userId.incrementAndGet();
       usersDto.setUserId(id);
       usersMap.put(id,usersDto);
       return usersDto;
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        if(usersMap.get(userId)!=null){
            usersMap.remove(userId);
            return true;
        }
        return false;
    }

    @Override
    public void editUser(Integer userId) {

    }
}
