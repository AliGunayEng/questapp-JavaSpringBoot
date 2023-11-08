package com.reactproject.questapp.Controller;

import com.reactproject.questapp.Entity.User;
import com.reactproject.questapp.Repository.UserRepository;
import com.reactproject.questapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
   private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    @GetMapping("/{userId}")
    public  User getOneUser(@PathVariable Long userId){

        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser){
       return userService.updateOneUser(userId, newUser);

    }

    @DeleteMapping("{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteOneUser(userId);
    }

}
