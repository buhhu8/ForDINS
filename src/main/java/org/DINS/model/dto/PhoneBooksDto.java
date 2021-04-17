package org.DINS.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Data
public class PhoneBooksDto {

    private Integer recordId;
    @Size(min=11, max = 11, message = "Number must have 11 characters")
    @Pattern(regexp = "^[0-9]+$")
    private String phoneNumber;

}
