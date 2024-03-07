package ru.clevertec.userClient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.clevertec.userClient.enums.Role;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
@Table(name = "users")
public class User implements UserDetails {

}
