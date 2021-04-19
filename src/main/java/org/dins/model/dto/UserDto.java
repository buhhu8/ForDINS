package org.dins.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserDto {

    private Integer userId;
    @NotEmpty(message = "User firstName couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String userName;
    private Map<Integer, PhoneBookRecordDto> phoneBook;

    public UserDto() {
        this.phoneBook = new HashMap<>();
    }

    public UserDto(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void addPhone(Integer userId, PhoneBookRecordDto dto){
        phoneBook.put(userId, dto);
    }

}
