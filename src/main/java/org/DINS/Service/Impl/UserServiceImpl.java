package org.DINS.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.UserService;
import org.DINS.findMethod.FindByName;
import org.DINS.model.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AtomicInteger userId = new AtomicInteger();
    private final Map<Integer, UsersDto> usersMap = new HashMap<>();

    @Override
    public Map<Integer, String> getAllUsers() {
        Map<Integer, String> resultMap = new HashMap<>();
        for (Map.Entry<Integer, UsersDto> entry : usersMap.entrySet()) {
            resultMap.put(entry.getKey(), entry.getValue().getUserName());
        }
        return resultMap;

    }

    @Override
    public UsersDto getUser(Integer id) {
        return usersMap.get(id);

    }

    @Override
    public UsersDto createUser(UsersDto usersDto) {
        final Integer id = userId.incrementAndGet();
        usersDto.setUserId(id);
        usersMap.put(id, usersDto);
        return usersDto;
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        if (usersMap.get(userId) != null) {
            usersMap.remove(userId);
            return true;
        }
        return false;
    }

    @Override
    public UsersDto editUser(Integer userId, UsersDto dto) {
        UsersDto usersDto = usersMap.get(userId);
        try {
            usersDto.setUserName(dto.getUserName());
            usersMap.put(userId, usersDto);
            return usersDto;
        } catch (NullPointerException exc) {
            return new UsersDto();
        }
    }

    @Override
    public Map<Integer, String> findByName(String name) {
        FindByName find = new FindByName();
        Map<Integer, String> resultMap = new HashMap<>();
        String foundName;
        for (Map.Entry<Integer, UsersDto> entry : usersMap.entrySet()) {
            foundName = entry.getValue().getUserName();
            if (find.getNameFirstEntry(foundName, name) != -1) {
                resultMap.put(entry.getKey(), entry.getValue().getUserName());
            }
        }
        return resultMap;
    }


}
