package org.dins.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithoutPhoneBookDto {

    private Integer userId;
    private String userName;

}
