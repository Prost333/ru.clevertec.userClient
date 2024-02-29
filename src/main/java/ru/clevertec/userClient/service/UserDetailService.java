package ru.clevertec.userClient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Сервис, реализующий интерфейс UserDetailsService для загрузки пользовательских данных в системе аутентификации Spring Security.
 * Получает данные о пользователе из UserService и возвращает UserDetails для аутентификации и авторизации.
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserService userService;
    /**
     * Загружает данные пользователя по его имени пользователя для использования в системе аутентификации Spring Security.
     *
     * @param username Имя пользователя, для которого нужно загрузить данные.
     * @return UserDetails объект, содержащий информацию о пользователе.
     * @throws UsernameNotFoundException Если пользователь с указанным именем не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails=userService.loadUserByUsername(username);
        return userDetails;
    }
}
