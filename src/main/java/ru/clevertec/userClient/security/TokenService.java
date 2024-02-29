package ru.clevertec.userClient.security;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.userClient.dto.UserResp;

@Service
public class TokenService {

    private String jwtToken;

    private UserResp userResp;

    public UserResp getUserResp() {
        return userResp;
    }

    public void setUserResp(UserResp userResp) {
        this.userResp = userResp;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public RestTemplate restTemplate(String jwtToken) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + jwtToken);
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}
