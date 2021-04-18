package org.DINS.Service.IMpl;

import org.DINS.Service.Impl.UserServiceImpl;
import org.DINS.model.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {

    private final UserServiceImpl userService = new UserServiceImpl();

    @Test
    @DisplayName("getUser: Should return null if no user with passed ID")
    public void testGetUser_userDoesNotExist_returnNull() {
        UserDto result = userService.getUser(495);
        Assertions.assertNull(result);
    }

}