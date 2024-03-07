package ru.clevertec.userClient.dto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.clevertec.userClient.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T13:06:52+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResp toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResp.UserRespBuilder userResp = UserResp.builder();

        userResp.username( user.getUsername() );
        userResp.password( user.getPassword() );
        userResp.role( user.getRole() );

        return userResp.build();
    }

    @Override
    public User toRequest(UserReq userReq) {
        if ( userReq == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( userReq.getUsername() );
        user.password( userReq.getPassword() );
        user.role( userReq.getRole() );

        return user.build();
    }
}
