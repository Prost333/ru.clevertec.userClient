package ru.clevertec.userClient.service.imp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import ru.clevertec.userClient.dao.UserDao;
import ru.clevertec.userClient.dto.UserMapper;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;
import ru.clevertec.userClient.security.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImpTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogout() throws ServletException {
        userService.logout();
        verify(tokenService).setJwtToken(null);
        verify(httpServletRequest).logout();
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        UserReq req = new UserReq();
        req.setRole(Role.SUBSCRIBER);
        req.setPassword("newpassword");
        req.setUsername("newusername");

        User user = new User();
        user.setId(id);
        user.setRole(Role.SUBSCRIBER);
        user.setPassword("oldpassword");
        user.setUsername("oldusername");

        when(userDao.findById(id)).thenReturn(Optional.of(user));

        UserResp expectedResponse = new UserResp();

        expectedResponse.setRole(Role.SUBSCRIBER);
        expectedResponse.setPassword("newpassword");
        expectedResponse.setUsername("newusername");

        when(userMapper.toResponse(user)).thenReturn(expectedResponse);

        UserResp updatedUser = userService.update(id, req);

        assertEquals(expectedResponse, updatedUser);
        assertEquals(req.getRole(), user.getRole());
        assertEquals(req.getPassword(), user.getPassword());
        assertEquals(req.getUsername(), user.getUsername());
        verify(userDao).save(user);
    }

    @Test
    public void testSave() {
        UserReq userReq = new UserReq();
        userReq.setRole(Role.SUBSCRIBER);
        userReq.setPassword("password");
        userReq.setUsername("username");

        User user = new User();
        user.setRole(Role.SUBSCRIBER);

        when(userMapper.toRequest(userReq)).thenReturn(user);

        UserResp expectedResponse = new UserResp();
        expectedResponse.setRole(Role.SUBSCRIBER);

        when(userMapper.toResponse(user)).thenReturn(expectedResponse);

        UserResp savedUser = userService.save(userReq);

        assertEquals(expectedResponse, savedUser);
        assertEquals(Role.SUBSCRIBER, user.getRole());
        verify(userDao).save(user);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setRole(Role.SUBSCRIBER);
        user.setPassword("password");
        user.setUsername("username");

        when(userDao.findById(id)).thenReturn(Optional.of(user));

        UserResp expectedResponse = new UserResp();
        expectedResponse.setRole(Role.SUBSCRIBER);
        expectedResponse.setPassword("password");
        expectedResponse.setUsername("username");

        when(userMapper.toResponse(user)).thenReturn(expectedResponse);

        UserResp foundUser = userService.findById(id);

        assertEquals(expectedResponse, foundUser);
    }

    @Test
    public void testFindByUsername() {
        String username = "username";
        User user = new User();
        user.setId(1L);
        user.setRole(Role.SUBSCRIBER);
        user.setPassword("password");
        user.setUsername(username);

        when(userDao.findByUsername(username)).thenReturn(Optional.of(user));

        UserResp expectedResponse = new UserResp();

        expectedResponse.setRole(Role.SUBSCRIBER);
        expectedResponse.setPassword("password");
        expectedResponse.setUsername(username);

        when(userMapper.toResponse(user)).thenReturn(expectedResponse);

        UserResp foundUser = userService.findByUsername(username);

        assertEquals(expectedResponse, foundUser);
    }

    @Test
    public void testFindByRole() {
        Role roleEnum = Role.SUBSCRIBER;

        User user1 = new User();
        user1.setId(1L);
        user1.setRole(roleEnum);
        user1.setPassword("password1");
        user1.setUsername("username1");

        User user2 = new User();
        user2.setId(2L);
        user2.setRole(roleEnum);
        user2.setPassword("password2");
        user2.setUsername("username2");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userDao.findByRole(roleEnum)).thenReturn(userList);

        UserResp expectedResponse1 = new UserResp();

        expectedResponse1.setRole(Role.SUBSCRIBER);
        expectedResponse1.setPassword("password1");
        expectedResponse1.setUsername("username1");

        UserResp expectedResponse2 = new UserResp();

        expectedResponse2.setRole(Role.SUBSCRIBER);
        expectedResponse2.setPassword("password2");
        expectedResponse2.setUsername("username2");

        when(userMapper.toResponse(user1)).thenReturn(expectedResponse1);
        when(userMapper.toResponse(user2)).thenReturn(expectedResponse2);

        List<UserResp> foundUsers = userService.findByRole(Role.SUBSCRIBER.name());

        assertEquals(2, foundUsers.size());
        assertEquals(expectedResponse1, foundUsers.get(0));
        assertEquals(expectedResponse2, foundUsers.get(1));
    }


}