package org.dins.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dins.model.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneBookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName(value = "createPhone: Should return 201 if user exists and phone number valid")
    public void testCreatePhone_userExistsPhoneValid_returnOk() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Denis\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89633410783\"}")
        )
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createPhone: Should return 400 if user exists and phone number shorter then need")
    public void testCreatePhone_userExistsPhoneNotValidMin_returnError() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Denis\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"8933410783\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createPhone: Should return 400 if user exists and phone number longer than need")
    public void testCreatePhone_userExistsPhoneNotValidMax_returnError() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Denis\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"893343307813\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createPhone: Should return 400 if user exists and lastName empty")
    public void testCreatePhone_userExistsPhoneNotValidLastNameEmpty_returnError() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Denis\", \"lastName\": \"\",\"phoneNumber\":\"89343307813\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createPhone: Should return 400 if user exists and lastName not valid")
    public void testCreatePhone_userExistsPhoneNotValidLastNameNotValid_returnError() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Denis\", \"lastName\": \"Dor1\",\"phoneNumber\":\"89343307813\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createPhone: Should return 400 if user exists and firstName empty")
    public void testCreatePhone_userExistsPhoneNotValidFirstNameEmpty_returnError() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \" \", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89343307813\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createPhone: Should return 400 if user exists and firstName notValid")
    public void testCreatePhone_userExistsPhoneNotValidFirstNameNotValid_returnError() throws Exception{
        putMap();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Den1\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89343307813\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }






    public MvcResult putMap() throws Exception{
        MvcResult userCreationResult = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Denis\"}")
        )
                .andReturn();
        return  userCreationResult;
    }

}
