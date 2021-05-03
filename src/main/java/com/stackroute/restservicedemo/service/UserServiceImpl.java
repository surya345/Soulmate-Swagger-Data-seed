package com.stackroute.restservicedemo.service;

import com.stackroute.restservicedemo.Exceptions.UserAlradyExistsException;
import com.stackroute.restservicedemo.model.User;
import com.stackroute.restservicedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepo;
    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) throws UserAlradyExistsException {

        if(userRepo.existsById(user.getId())){
            throw new UserAlradyExistsException();
        }
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return (List<User>) userRepo.findAll();
    }
    @Override
    public Long fetchCountUser() {
        Long usersCount = userRepo.count();
        return usersCount;
    }


    @Override
    public String deleteUser(int id) throws NoSuchElementException {
        Optional<User> myuser=userRepo.findById(id);
        if(myuser.get().getId()!=id){
            throw new NoSuchElementException();
        }
        userRepo.deleteById(id);
        return "Success";
    }

    @Override
    public Optional<User> getUserById(int id) throws NoSuchElementException{
        Optional<User> myuser=userRepo.findById(id);
        if(myuser.get().getId()!=id){
            throw new NoSuchElementException();
        }
        return myuser;
    }

    @Override
    public User updateUser(int id, User user) throws NoSuchElementException{
        Optional<User> myuser=userRepo.findById(id);
        if(myuser.get().getId()!=id){
            throw new NoSuchElementException();
        }
        user.setId(id);
        return userRepo.save(user);
    }


}
