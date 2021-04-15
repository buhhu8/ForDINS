package org.DINS.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Data
public class NumberInPhoneBookDto {

    private Integer recordId;
    @NotEmpty(message = "Number could not be empty")
    @Size(min=11, max = 11, message = "Number must have 11 characters")
    private String phoneNumber;

    private Map<Integer,String> map =new HashMap<>();

    public void add(){
        map.put(this.recordId,this.phoneNumber);
    }

}
