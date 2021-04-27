package org.dins.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dins.model.dto.UserDto;
import org.dins.model.dto.UserWithoutPhoneBookDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUser_whenUserDoesNotExist_thenReturnBadRequestStatus() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/users/108"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Assertions.assertTrue(body.contains("User with such ID 108 doesn't exist"));
    }

    @Test
    public void testGetUser_whenUserExists_thenReturnUser() throws Exception {
        MvcResult userCreationResult = mockMvc.perform(
                post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"Denis\"}")
        )
                .andExpect(status().isOk())
                .andReturn();

        UserDto user = objectMapper.readValue(userCreationResult.getResponse().getContentAsString(), UserDto.class);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/" + user.getUserId()))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains(user.getUserName()));
    }

    @Test
    public void testGetAllUsers_whenUsersDoesNotExist_thenReturnEmptyList() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/v1/users/"))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Assertions.assertTrue(body.equals("[]"));

    }

    @Test
    public void testGetAllUsers_UserExists_returnListOfUsers() throws Exception{
        MvcResult userCreationResult  = mockMvc.perform(
                post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\": \"Denis\"}")
        )
                .andExpect(status().isOk())
                .andReturn();

        UserWithoutPhoneBookDto user = objectMapper.readValue(userCreationResult.getResponse().getContentAsString(), UserWithoutPhoneBookDto.class);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(result.contains(user.getUserName()));

    }
}
