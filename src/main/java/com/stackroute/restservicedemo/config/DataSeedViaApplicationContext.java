package com.stackroute.restservicedemo.config;

import com.stackroute.restservicedemo.Exceptions.UserAlradyExistsException;
import com.stackroute.restservicedemo.model.User;
import com.stackroute.restservicedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "com.stackroute.restservicedemo")
@Component

public class DataSeedViaApplicationContext implements ApplicationListener<ContextRefreshedEvent> {
        @Autowired
        private UserService userService;

        public DataSeedViaApplicationContext(UserService userService) {
                this.userService =userService;
        }

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
                try {
                        userService.saveUser(new User(400, "Varsha","D", "Female", 25));
                } catch (UserAlradyExistsException e) {
                        e.printStackTrace();
                }
        }

}






