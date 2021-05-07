package org.dins.contoller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dins.controller.UserController;
import org.dins.model.dto.PhoneBookRecordDto;
import org.dins.model.dto.UserDto;
import org.dins.service.UserService;
import org.dins.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneBookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DisplayName(value = "createPhone: Should return 201 if user exists and phone number valid")
    public void testCreatePhone_userExistsPhoneValid_returnOk() throws Exception {
        putMapUsers();
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
    public void testCreatePhone_userExistsPhoneNotValidMin_returnError() throws Exception {
        putMapUsers();
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
    public void testCreatePhone_userExistsPhoneNotValidMax_returnError() throws Exception {
        putMapUsers();
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
    public void testCreatePhone_userExistsPhoneNotValidLastNameEmpty_returnError() throws Exception {
        putMapUsers();
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
    public void testCreatePhone_userExistsPhoneNotValidLastNameNotValid_returnError() throws Exception {
        putMapUsers();
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
    public void testCreatePhone_userExistsPhoneNotValidFirstNameEmpty_returnError() throws Exception {
        putMapUsers();
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
    public void testCreatePhone_userExistsPhoneNotValidFirstNameNotValid_returnError() throws Exception {
        putMapUsers();
        mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Den1\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89343307813\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "getAllPhonesByUserId: Should return 400 if user doesn't exist")
    public void testGetAllPhonesByUserId_userDoesNotExist_returnError() throws Exception {
        mockMvc.perform(get("/api/v1/phones/155"))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName(value = "getAllPhonesByUserId: Should return list of  all phones for user")
    public void testGetAllPhonesByUserId_UserExists_returnListOfAllPhines() throws Exception {
        putMapUsers();
        putMapPhones();

        MvcResult phonesCollection = mockMvc.perform(get("/api/v1/phones/1"))
                .andExpect(status().isOk())
                .andReturn();
        String expectedResult = phonesCollection.getResponse().getContentAsString();
        Assertions.assertTrue(expectedResult.contains("Anton"));
    }

    @Test
    @DisplayName(value = "getPhoneById: Should return 400 if user doesn't exist")
    public void testPhoneById_userDoesNotExist_returnError() throws Exception {
        mockMvc.perform(get("/api/v1/phones/150/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "getPhoneById: Should return 400 if user exists phone doesn't exist")
    public void testGetPhoneById_UserExistsPhoneDoesNotExist_returnError() throws Exception{
        putMapUsers();
        mockMvc.perform(get("/api/v1/phones/1/150"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "getPhoneById: Should return phone for user by phone id")
    public void testGetPhoneById_UserExistsPhoneExists_returnPhone() throws Exception {
        putMapUsers();
        putMapPhones();
        MvcResult getResult = mockMvc.perform(get("/api/v1/phones/1/1"))
                .andExpect(status().isOk())
                .andReturn();
        String result = getResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains("Anton"));
    }

    @Test
    @DisplayName(value = "deletePhoneById: Should return 400 if user doesn't exist")
    public void testDeletePhoneById_userDoesNotExist_returnError() throws Exception{
        mockMvc.perform(delete("/api/v1/phones/150/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "deletePhoneById: Should return 400 if user exists phone doesn't exist ")
    public void testDeletePhoneById_userExistsPhoneDoesNorExist_returnError() throws Exception{
        putMapUsers();
        mockMvc.perform(delete("/api/v1/phones/1/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "deletePhoneById: Should return 200 if user exists phone exists")
    public void testDeletePhoneById_userExistsPhoneDoesNotExist_returnOk() throws Exception{
        putMapUsers();
        putMapPhones();
        mockMvc.perform(delete("/api/v1/phones/1/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/phones/1/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "editPhoneById: Should return 400 if user doesn't exist")
    public void testEditPhoneById_userDoesNotExist_returnError() throws Exception{
        mockMvc.perform(put("/api/v1/phones/150/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "editPhoneById: Should return 400 if number doesn't exist")
    public void testEditPhoneById_userExistsPhoneDoesNotExist_returnError() throws Exception{
        putMapUsers();
        mockMvc.perform(put("/api/v1/phones/1/150"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "editPhoneById: Should return 400 if firstName doesn't valid")
    public void testEditPhoneById_userExistsPhoneExistsFirstNameNotValid_returnError() throws Exception{
        putMapUsers();
        putMapPhones();
        mockMvc.perform(put("/api/v1/phones/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Deni1s\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89633410783\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "editPhoneById: Should return 400 if lastName doesn't valid")
    public void testEditPhoneById_userExistsPhoneExistsLastNameNotValid_returnError() throws Exception{
        putMapUsers();
        putMapPhones();
        mockMvc.perform(put("/api/v1/phones/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Denis\", \"lastName\": \"D1eev\",\"phoneNumber\":\"89633410783\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "editPhoneById: Should return 400 if phoneNumder doesn't valid")
    public void testEditPhoneById_userExistsPhoneExistsPhoneNumberNotValid_returnError() throws Exception{
        putMapUsers();
        putMapPhones();
        mockMvc.perform(put("/api/v1/phones/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Denis\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"896334107a3\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "editPhoneById: Should return Ok")
    public void testEditPhoneById_userExistsPhoneExistsDataIsValid_returnOk() throws Exception{
        putMapUsers();
        MvcResult resultPut = putMapPhones();
        MvcResult resultEdit= mockMvc.perform(put("/api/v1/phones/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Denis\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89633410783\"}"))
                .andExpect(status().isOk())
                .andReturn();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
 //       PhoneBookRecordDto originalData = objectMapper.readValue(resultPut.getResponse().getContentAsString(),PhoneBookRecordDto.class);
   //     PhoneBookRecordDto editData = objectMapper.readValue(resultEdit.getResponse().getContentAsString(),PhoneBookRecordDto.class);
   //     Assertions.assertNotEquals(originalData.getFirstName(),editData.getFirstName());

    }

    @Test
    @DisplayName(value = "findByNumber: Should return 400 if user doesn't exist")
    public void testFindByNumber_userDoesNotExist_returnError() throws Exception{
        mockMvc.perform(get("/api/v1/phones/search/150")
                .param("findNumber", "89633410783"))
                .andExpect(status().isBadRequest());
    }
//todo
    @Test
    @DisplayName(value = "findByNumber: Should return all phone number for user by userId and number or part of number")
    public void testFindByNumber_userExists_returnAllRecords() throws Exception{
        putMapUsers();
        putMapPhones();
        MvcResult expectedResult = mockMvc.perform(get("/api/v1/phones/search/1")
                .param("findNumber", "8963"))
                .andExpect(status().isOk())
                .andReturn();
        String result = expectedResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains("8963"));
    }


    public MvcResult putMapUsers() throws Exception {
        MvcResult userCreationResult = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Denis\"}")
        )
                .andReturn();
        return userCreationResult;
    }

    public MvcResult putMapPhones() throws Exception {
        return mockMvc.perform(
                post("/api/v1/phones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Anton\", \"lastName\": \"Dorofeev\",\"phoneNumber\":\"89633410783\"}")
        )
                .andReturn();
    }

}
