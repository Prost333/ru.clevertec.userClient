package ru.clevertec.userClient.dao;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@Testcontainers
class UserDaoTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    @Autowired
    private UserDao userDao;

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void testFindByUsername() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setRole(Role.SUBSCRIBER);
        user.setPassword("32124");
        userDao.save(user);

        // Act
        Optional<User> foundUser = userDao.findByUsername("testuser");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void testFindByRole() {
        // Arrange
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("1232451");
        user1.setRole(Role.SUBSCRIBER);
        userDao.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("13352");
        user2.setRole(Role.ADMIN);
        userDao.save(user2);

        // Act
        List<User> usersWithRoleUser = userDao.findByRole(Role.SUBSCRIBER);

        // Assert
        assertEquals(userDao.findByRole(Role.SUBSCRIBER).size(), usersWithRoleUser.size());
        assertEquals(true, usersWithRoleUser.contains(user1));
    }
}