package org.dins.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithoutPhoneBookDto {

    @ApiModelProperty(name = "userId", value = "Return unique id in userDto.")
    private Integer userId;
    @ApiModelProperty(name = "userName", value = " Return user name")
    private String userName;

}
