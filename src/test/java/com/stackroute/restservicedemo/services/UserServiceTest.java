package com.stackroute.restservicedemo.services;

import com.stackroute.restservicedemo.Exceptions.UserAlradyExistsException;
import com.stackroute.restservicedemo.Exceptions.UserNotFoundException;
import com.stackroute.restservicedemo.model.User;
import com.stackroute.restservicedemo.repository.UserRepository;
import com.stackroute.restservicedemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws UserAlradyExistsException {
        User user=new User(1,"Vino","David","Male",25);
        when(userRepository.save(any())).thenReturn(user);
        userService.saveUser(user);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public  void listallTheUsers(){
        User user=new User(1,"Vino","David","Male",25);
        userRepository.save(user);
        List <User> userList = userService.getAllUser();
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userList,userList);
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findAll();

    }

    @Test
    public  void deleteUser() throws UserNotFoundException {
        int id=2;
        User user=new User(1,"Vino","David","Male",25);
        userService.deleteUser(id);
        verify(userRepository,times(1)).deleteById(id);

    }
}