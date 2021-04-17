package org.DINS.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@Data
public class UsersDto {

    private Integer userId;
    @NotEmpty(message = "User name couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String userName;
    private Map<Integer, PhoneBooksDto> phoneBooksDtoMap = new HashMap<Integer, PhoneBooksDto>();

    public void addIntoUsers(Integer userId, PhoneBooksDto dto){
        phoneBooksDtoMap.put(userId,dto);
    }

}
