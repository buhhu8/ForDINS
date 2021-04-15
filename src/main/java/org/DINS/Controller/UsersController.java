package org.DINS.Controller;


import lombok.RequiredArgsConstructor;
import org.DINS.Service.Impl.UserServiceImpl;
import org.DINS.model.dto.UsersDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UsersController {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UsersDto> getUser(@PathVariable Integer userId) {
        UsersDto usersDto =userService.getUser(userId);
        if(usersDto!=null){
            return new ResponseEntity<>(usersDto,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> usersDtoList =userService.getAllUsers();
        if(usersDtoList!=null){
            return new ResponseEntity<>(usersDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UsersDto usersDto) {
        if (userService.createUser(usersDto) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        if (userService.deleteUser(userId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
