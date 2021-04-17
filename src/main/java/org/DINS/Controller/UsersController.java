package org.DINS.Controller;


import lombok.RequiredArgsConstructor;
import org.DINS.Service.Impl.UserServiceImpl;
import org.DINS.model.dto.UsersDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UsersDto> getUser(@PathVariable Integer userId) {
        UsersDto usersDto = userService.getUser(userId);
        if (usersDto != null) {
            return new ResponseEntity<>(usersDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Map<Integer,String>> getAllUsers() {
        Map<Integer,String> usersDtoList = userService.getAllUsers();
        if (usersDtoList.get(1)!=null){
            return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
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

    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Integer userId, @RequestBody @Valid UsersDto dto) {
        UsersDto usersDto = userService.editUser(userId, dto);
        if (usersDto.getUserName() != null) {
            return new ResponseEntity<>(usersDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findName(@PathVariable String name){
        Map<Integer, String> resultMap = userService.findByName(name);
        if(resultMap.get(1)!=null){
            return new ResponseEntity(resultMap,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
