package org.DINS.model.dto;

import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Setter
public class PhoneBooksDto {

    Integer phoneBookId;
    Map<Integer,NumberInPhoneBookDto> numberInPhoneBookDtoMap = new HashMap<Integer, NumberInPhoneBookDto>();

}
