package org.dins.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserDto {

    @ApiModelProperty(name = "userId", value = "unique id in userDto. Doesn't send, auto increment")
    private Integer userId;

    @ApiModelProperty(name = "userName", value = "For example: Anton. Couldn't be empty. Russian and English languages only")
    @NotEmpty(message = "User firstName couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String userName;

    @ApiModelProperty(name = "phoneBook", value = "Map for storage PhoneBookRecordDto")
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
