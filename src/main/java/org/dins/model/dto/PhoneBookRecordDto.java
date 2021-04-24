package org.dins.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PhoneBookRecordDto {

    @ApiModelProperty(name = "recordId",required = false, notes = "number unique code in phone book. Doesn't send, auto increment")
    private Integer recordId;

    @ApiModelProperty(name = "firstName",required = true, value = "Denis", notes = "can use Russian and English languages ")
    @NotEmpty(message = "User firstName couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String firstName;

    @ApiModelProperty(name = "lastName",required = true, value = "Dorofeev", notes = "can use Russian and English languages ")
    @NotEmpty(message = "User lastName couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String lastName;

    @ApiModelProperty(name = "phoneNumber",required = true, value = "89633410783", notes = "only numbers ")
    @Size(min=11, max = 11, message = "Number must have 11 characters")
    @Pattern(regexp = "^[0-9]+$")
    private String phoneNumber;

}
