package ru.clevertec.userClient.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.userClient.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {
    @NotBlank
    @Size(max = 50)
    private String username;
    @NotBlank
    @Size(max = 50)
    private String password;

    private Role role;
    private String jwt;
}
