package ru.clevertec.userClient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс доступа к данным для сущности пользователей.
 * Предоставляет методы для выполнения операций с данными пользователей в базе данных.
 */
public interface UserDao extends JpaRepository<User,Long> {
    /**
     * Находит пользователя по его имени пользователя.
     *
     * @param username Имя пользователя для поиска.
     * @return Объект типа Optional, содержащий найденного пользователя, если он существует.
     */
    Optional<User> findByUsername(String username);
    /**
     * Находит список пользователей с заданной ролью.
     *
     * @param role Роль пользователей для поиска.
     * @return Список пользователей с указанной ролью.
     */
    List<User> findByRole(Role role);

}
