package org.DINS.model.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Data
public class UsersDto {

    private Integer userId;
    @NotEmpty(message = "User name couldn't be empty")
    private String userName;
    private Map<Integer,PhoneBooksDto> phoneBooksDtoMap = new HashMap<Integer, PhoneBooksDto>();

}
