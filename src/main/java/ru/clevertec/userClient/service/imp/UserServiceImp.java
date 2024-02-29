package ru.clevertec.userClient.service.imp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.clevertec.userClient.dao.UserDao;
import ru.clevertec.userClient.dto.UserMapper;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;
import ru.clevertec.userClient.exeption.EntityNotFoundException;
import ru.clevertec.userClient.security.TokenService;
import ru.clevertec.userClient.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final HttpServletRequest httpServletRequest;
    private final TokenService tokenService;


    @Override
    public void logout() throws ServletException {
        tokenService.setJwtToken(null);
        httpServletRequest.logout();
    }

    @Override
    public UserResp update(Long id, UserReq req) {
        User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setRole(req.getRole());
        user.setPassword(req.getPassword());
        user.setUsername(req.getUsername());
        userDao.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResp save(UserReq userReqReq) {
        try {
            User user = userMapper.toRequest(userReqReq);
            user.setRole(ru.clevertec.userClient.enums.Role.SUBSCRIBER);
            userDao.save(user);
            return userMapper.toResponse(user);
        } catch (Throwable e) {
            throw new EntityNotFoundException("there was a registration error");
        }
    }

    @Override
    public UserResp findById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResp findByUsername(String username) {
        User user = userDao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }


    @Override
    public List<UserResp> findByRole(String role) {
        try {
            Role roleEnum = Role.valueOf(role.toUpperCase());
            List<User> users = userDao.findByRole(roleEnum);
            return users.stream().map(userMapper::toResponse).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Invalid role: " + role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userDao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user;
    }

    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }
}

