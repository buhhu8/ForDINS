package org.DINS.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@Data
public class UsersDto {

    private Integer userId;
    @NotEmpty(message = "User name couldn't be empty")
    private String userName;
    private Map<Integer, PhoneBooksDto> phoneBooksDtoMap = new HashMap<Integer, PhoneBooksDto>();

    public void addIntoUsers(Integer userId, PhoneBooksDto dto){
        phoneBooksDtoMap.put(userId,dto);
    }

}
