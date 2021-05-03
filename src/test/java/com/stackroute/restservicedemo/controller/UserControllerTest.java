package com.stackroute.restservicedemo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.restservicedemo.model.User;
import com.stackroute.restservicedemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    private User user;
    private List<User> userList;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp()
    {
        User user=new User(1,"Vino","David","Male",25);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }
    public  static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws Exception {
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(userService, times(1)).saveUser(any());

    }

    @Test
    public void getAllTheUsersInList() throws Exception {
        when(userService.getAllUser()).thenReturn(userList);
        mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
        verify(userService, times(1)).getAllUser();

    }

    @Test
    public void UpdateUser() throws Exception {
        when(userService.updateUser(anyInt(),any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/update/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isNoContent());
        verify(userService, times(1)).updateUser(anyInt(),any());

    }

    @Test
    public void DeleteUser() throws Exception {

        when(userService.deleteUser(user.getId())).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userService,times(1)).deleteUser(user.getId());

    }



}
