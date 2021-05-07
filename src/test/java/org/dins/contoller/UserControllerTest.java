package org.dins.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dins.model.dto.UserDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName(value = "getAllUsers: Should return all users")
    public void testGetAllUsers_UserExists_returnListOfUsers() throws Exception {
        MvcResult userCreationResult = initialize();

        UserDto user = objectMapper.readValue(userCreationResult.getResponse().getContentAsString(), UserDto.class);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/"))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains(user.getUserName()));
    }

    @Test
    @DisplayName(value = "getUser: Should return bad request when user doesn't exist")
    public void testGetUser_UserDoesNotExist_ReturnBadRequestStatus() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/users/108"))
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        Assertions.assertTrue(body.contains("User with such ID 108 doesn't exist"));
    }

    @Test
    @DisplayName(value = "getUser: Should return user when user exists")
    public void testGetUser_UserExists_ReturnUser() throws Exception {
        MvcResult userCreationResult = initialize();
        UserDto user = objectMapper.readValue(userCreationResult.getResponse().getContentAsString(), UserDto.class);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/" + user.getUserId()))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains(user.getUserName()));
    }

    @Test
    @DisplayName(value = "createUser: Should create new user")
    public void testCreateUser_userValid_returnNewUser() throws Exception {
        MvcResult userCreationResult = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Denis\"}")
        )
                .andExpect(status().isOk())
                .andReturn();
        UserDto user = objectMapper.readValue(userCreationResult.getResponse().getContentAsString(), UserDto.class);
        Assertions.assertFalse(user.getUserName().isEmpty());

    }

    @Test
    @DisplayName(value = "createUser: Should return error if user empty")
    public void testCreateUser_userDoesNotValidIfEmpty_returnError() throws Exception {
        MvcResult userCreation = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "createUser: Should return error if user not valid")
    public void testCreateUser_userDoesNotValid_returnError() throws Exception {
        MvcResult userCreation = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"1222\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    @DisplayName(value = "deleteUser: Should return 200 if user was deleted")
    public void testDeleteUser_userExists_returnOk() throws Exception {
        initialize();
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName(value = "deleteUser: Should return 404 if user doesn't exist")
    public void testDeleteUser_userDoesNotExist_returnNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1222"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName(value = "editUser: Should return 200 if user exists and editUser")
    public void testEditUser_userExistsAndValid_returnOK() throws Exception {
        MvcResult creationResult = initialize();
        MvcResult expectedResult = mockMvc.perform(
                put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Anton\"}")
        )
                .andExpect(status().isOk())
                .andReturn();
        UserDto result = objectMapper.readValue(expectedResult.getResponse().getContentAsString(), UserDto.class);
        Assertions.assertTrue(result.getUserName().contains("Anton"));
    }

    @Test
    @DisplayName(value = "editUser: Should return 400 if user doesn't exist")
    public void testEditUser_userDoesNotExist_returnError() throws Exception {
        MvcResult result = mockMvc.perform(
                put("/api/v1/users/1323")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Anton\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "editUser: Should return 400 if data not valid")
    public void testEditUser_userExistsAndDataNotValid_returnError() throws Exception {
        MvcResult result = mockMvc.perform(
                put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Ant23on\"}")
        )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName(value = "findName: Should return all found users")
    public void testFindName_userExists_returnListofUsers() throws Exception {
        initialize();
        MvcResult expectedResult = mockMvc.perform(get("/api/v1/users/search/")
                .param("firstName", "Den"))
                .andExpect(status().isOk())
                .andReturn();
        String result = expectedResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains("Denis"));
    }


    public MvcResult initialize() throws Exception {
        MvcResult userCreationResult = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Denis\"}")
        )
                .andExpect(status().isOk())
                .andReturn();
        return userCreationResult;
    }

}
