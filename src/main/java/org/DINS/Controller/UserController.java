package org.DINS.Controller;

import lombok.RequiredArgsConstructor;
import org.DINS.Service.Impl.UserServiceImpl;
import org.DINS.exception.UserNotFoundException;
import org.DINS.model.dto.UserDto;
import org.DINS.model.dto.UserWithoutPhoneBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Integer userId) {
        UserDto userDto = userService.getUser(userId);
        if (userDto == null) {
            throw new UserNotFoundException(userId);
        }
        return userDto;
    }

    @GetMapping
    public Collection<UserWithoutPhoneBookDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        boolean deleteResult = userService.deleteUser(userId);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{userId}")
    public UserDto editUser(@PathVariable Integer userId, @RequestBody @Valid UserDto dto) {
        return userService.editUser(userId, dto);
    }

    @GetMapping("/search")
    public Collection<UserWithoutPhoneBookDto> findName(@RequestParam("name") String name) {
        return userService.findByName(name);
    }
}
