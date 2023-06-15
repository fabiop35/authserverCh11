package com.security.test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
 
import com.security.entities.User;
import com.security.services.UserService;

@WebMvcTest
public class AuthControllerTest{
 
 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private UserService userService;

  @Test
  public void testAuthService() 
               throws Exception {
    System.out.println("### Test AuthController ###");
    mockMvc.perform(MockMvcRequestBuilders. 
     post("/user/auth")
     .content(asJsonString(new User("anibal", "")))
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andExpect(
       //status().isUnauthorized() );
       status().isOk() );
  }

  public static String asJsonString(final Object obj) {
    try {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

}
