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

}

