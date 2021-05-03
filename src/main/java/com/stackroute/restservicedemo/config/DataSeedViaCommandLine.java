package com.stackroute.restservicedemo.config;
import com.stackroute.restservicedemo.model.User;
import com.stackroute.restservicedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@ComponentScan(basePackages = "com.stackroute.restservicedemo")
@Component
public class DataSeedViaCommandLine implements CommandLineRunner {
    @Autowired
    private UserService userService;
    public DataSeedViaCommandLine(UserService userService) {
        this.userService =userService;
    }
    @Override
    public void run(String... args) throws Exception {
        userService.saveUser(new User(200, "VENU","Bhai" ,"Male", 25));
    }
}