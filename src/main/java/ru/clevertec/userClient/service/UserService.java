package ru.clevertec.userClient.service;

import jakarta.servlet.ServletException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;

import ru.clevertec.userClient.enums.Role;

import java.util.List;

public interface UserService {

    UserResp update(Long id, UserReq req);

    UserResp save(UserReq userReqReq);

    UserResp findById(Long id);

    UserResp findByUsername(String username);

    List<UserResp> findByRole(String role);

    UserDetails loadUserByUsername(String username);

    void logout() throws ServletException;

    UserDetailsService userDetailsService();

}
