package com.stackroute.restservicedemo.controller;

import com.stackroute.restservicedemo.Exceptions.UserAlradyExistsException;
import com.stackroute.restservicedemo.model.User;
import com.stackroute.restservicedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class UserController {
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws UserAlradyExistsException,Exception  {
        logger.info("Saves a user");
        User savedUser= userService.saveUser(user);
        logger.debug("Saved a user");
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()throws Exception{
        logger.debug("Displays  all user");
        return  new ResponseEntity<List<User>>((List<User>) userService.getAllUser(),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) throws NoSuchElementException,Exception {
        userService.deleteUser(id);
        logger.info("Deletion of specific user");
        return new ResponseEntity<String>("Success",HttpStatus.OK);

    }
    @GetMapping("/users/{id}")
    public User userById(@PathVariable int id) throws NoSuchElementException,Exception{
        Optional<User> user = userService.getUserById(id);
        logger.debug("Displays specific user");
        return user.get();
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateById(@PathVariable int id,@RequestBody User user) throws NoSuchElementException,Exception{
        userService.updateUser(id,user);
        logger.info("Updation of specific user");
        return ResponseEntity.noContent().build();
    }


}
