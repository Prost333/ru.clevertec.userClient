package ru.clevertec.userClient.service.imp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;
import ru.clevertec.userClient.exeption.EntityNotFoundException;
import ru.clevertec.userClient.security.JwtService;
import ru.clevertec.userClient.security.TokenService;
import ru.clevertec.userClient.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    private final HttpServletRequest httpServletRequest;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public UserResp signUp(UserReq request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.SUBSCRIBER)
                .build();

        userService.save(request);

        var jwt = jwtService.generateToken(user);
        return new UserResp(request.getUsername(), request.getPassword(), Role.SUBSCRIBER, jwt);
    }


    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public UserResp signIn(UserReq request) {
        try {
            if (request.getUsername() == null || request.getUsername().isEmpty() ||
                    request.getPassword() == null || request.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Login and password must not be empty");
            }
            httpServletRequest.login(request.getUsername(), request.getPassword());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            var user = userService
                    .loadUserByUsername(request.getUsername());
            var jwt = jwtService.generateToken(user);
            UserResp userResp = userService.findByUsername(request.getUsername());
            tokenService.setJwtToken(jwt);
            tokenService.setUserResp(userResp);
            return new UserResp(userResp.getUsername(), userResp.getPassword(),
                    userResp.getRole(), jwt);
        } catch (ServletException e) {
            throw new EntityNotFoundException("fail login");
        }

    }
}