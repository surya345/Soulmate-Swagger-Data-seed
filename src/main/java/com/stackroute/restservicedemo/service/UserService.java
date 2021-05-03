package com.stackroute.restservicedemo.service;

import com.stackroute.restservicedemo.Exceptions.UserAlradyExistsException;
import com.stackroute.restservicedemo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


public interface UserService {
        Long fetchCountUser() throws NoSuchElementException;
        User saveUser(User user) throws UserAlradyExistsException;
        List<User> getAllUser();
        String deleteUser(int id) throws NoSuchElementException;
        Optional<User> getUserById(int id)throws NoSuchElementException;;
        User updateUser(int id,User user)throws NoSuchElementException;;

}