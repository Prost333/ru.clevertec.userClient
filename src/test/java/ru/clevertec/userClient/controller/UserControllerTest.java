package ru.clevertec.userClient.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;
import ru.clevertec.userClient.security.JwtService;
import ru.clevertec.userClient.service.UserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    private void authenticateAsAdmin() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
        User user = User.builder().role(Role.ADMIN).password("admin123").username("admin").build();
        UserDetails userDetails = user;
        String token = jwtService.generateToken(userDetails);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    @Test
    public void testFindById() throws Exception {
        authenticateAsAdmin();

        UserResp userResp = new UserResp();
        when(userService.findById(anyLong())).thenReturn(userResp);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(userResp)));

        verify(userService, times(1)).findById(anyLong());
    }
    @Test
    public void testUpdate() throws Exception {
        authenticateAsAdmin();

        UserResp userResp = new UserResp();
        when(userService.update(anyLong(), any(UserReq.class))).thenReturn(userResp);

        mockMvc.perform(put("/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new UserReq())))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(userResp)));

        verify(userService, times(1)).update(anyLong(), any(UserReq.class));
    }

    @Test
    public void testFindByUsername() throws Exception {
        authenticateAsAdmin();

        UserResp userResp = new UserResp();
        when(userService.findByUsername(anyString())).thenReturn(userResp);

        mockMvc.perform(get("/users/username/{username}", "admin"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(userResp)));

        verify(userService, times(1)).findByUsername(anyString());
    }

    @Test
    public void testFindByRole() throws Exception {
        authenticateAsAdmin();

        List<UserResp> userResps = new ArrayList<>();
        when(userService.findByRole(anyString())).thenReturn(userResps);

        mockMvc.perform(get("/users/role/{role}", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(userResps)));

        verify(userService, times(1)).findByRole(anyString());
    }
}
