package org.dins.service.impl;

import lombok.RequiredArgsConstructor;
import org.dins.service.UserService;
import org.dins.exception.UserNotFoundException;
import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AtomicInteger userId = new AtomicInteger();
    private final Map<Integer, UserDto> usersMap = new HashMap<>();

    @Override
    public Collection<UserWithoutPhoneBookDto> getAllUsers() {
        return usersMap.entrySet().stream()
                .map(entry -> new UserWithoutPhoneBookDto(entry.getKey(), entry.getValue().getUserName()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Integer id) {
        return usersMap.get(id);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Integer id = userId.incrementAndGet();
        userDto.setUserId(id);
        usersMap.put(id, userDto);
        return userDto;
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
    public UserDto editUser(Integer userId, UserDto dto) {
        UserDto userDto = usersMap.get(userId);
        if (userDto == null) {
             throw new UserNotFoundException(userId);
        }

        userDto.setUserName(dto.getUserName());
        usersMap.put(userId, userDto);
        return userDto;
    }

    @Override
    public Collection<UserWithoutPhoneBookDto> findByName(String name) {
        return usersMap.entrySet().stream()
                .filter(entry -> entry.getValue().getUserName().contains(name))
                .map(entry -> new UserWithoutPhoneBookDto(entry.getKey(), entry.getValue().getUserName()))
                .collect(Collectors.toList());
    }


}
