//package drtx.cleanArchitecture.example.infraestructure.web;
//
//import drtx.cleanArchitecture.example.application.dto.UserDTO;
//import drtx.cleanArchitecture.example.application.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @Test
//    public void testGetUser() throws Exception {
//        // Arrange
//        UserDTO userDTO = new UserDTO(1L, "John", "john@example.com");
//        given(userService.getUserById(1L)).willReturn(userDTO);
//
//        // Act & Assert
//        mockMvc.perform(get("/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John"))
//                .andExpect(jsonPath("$.email").value("john@example.com"));
//    }
//}