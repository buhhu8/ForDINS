package org.dins.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.dins.exception.UserNotFoundException;
import org.dins.model.dto.ErrorMessageDto;
import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;
import org.dins.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user from userDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    public UserDto getUser(@PathVariable Integer userId) {
        UserDto userDto = userService.getUser(userId);
        if (userDto == null) {
            throw new UserNotFoundException(userId);
        }
        return userDto;
    }

    @GetMapping
    @ApiOperation(value = "Get all users from userDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    public Collection<UserWithoutPhoneBookDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ApiOperation(value = "Create new user in userDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "\"userName\": \"User firstName couldn't be empty\"")
    })
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Delete user from userDto by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        boolean deleteResult = userService.deleteUser(userId);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Edit user in userDto by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorMessageDto.class, message = "Bad Request")
    })
    public UserDto editUser(@PathVariable Integer userId, @RequestBody @Valid UserDto dto) {
        return userService.editUser(userId, dto);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Find user in userDto by name or part of name")
    @ApiResponses(value = {
            @ApiResponse(code = 200,response = UserWithoutPhoneBookDto.class ,message = "OK"),
    })
    public Collection<UserWithoutPhoneBookDto> findName(@RequestParam("firstName") String name) {
        return userService.findByName(name);
    }
}
