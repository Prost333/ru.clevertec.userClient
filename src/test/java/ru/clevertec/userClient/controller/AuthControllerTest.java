package ru.clevertec.userClient.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSignUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/sign-up")
                        .content("{\"username\":\"test\",\"password\":\"test\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSignIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/sign-in")
                        .content("{\"username\":\"test\",\"password\":\"test\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/auth/getBody")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}