package org.DINS.model.dto;

import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
public class PhoneBooksDto {

    Integer phoneBookId;

    Map<Integer,NumberInPhoneBookDto> numberInPhoneBookDtoMap = new HashMap<Integer, NumberInPhoneBookDto>();

    public void addIntoMap(Integer id, NumberInPhoneBookDto dto){
        numberInPhoneBookDtoMap.put(id,dto);
    }

}
