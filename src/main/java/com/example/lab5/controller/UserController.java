package com.example.lab5.controller;

import com.example.lab5.Users.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {
    private List<Users> usersList = new ArrayList<>();


    public UserController() {
        Users u1 = new Users("1", "Nguyen Van A", "a@donga.edu");
        Users u2 = new Users("2", "Nguyen Van B", "b@donga.edu");
        usersList.add(u1);
        usersList.add(u2);
    }


    @GetMapping("/user")
    @ResponseBody
    public List<Users> getUsersList() {
        return usersList;
    }



    @GetMapping("users/{id}")
    @ResponseBody
    public ResponseEntity<Users> getUserById(@PathVariable("id") String userId) {
        for (Users user : usersList) {
            if (user.getId().equals(userId)) {
                return ResponseEntity.status(200).body(user);
            }
        }
        return ResponseEntity.status(404).body(null);
    }



    @DeleteMapping("users/{id}")
    @ResponseBody
    public List<Users> deleteUser(@PathVariable("id") String userId) {
        for (Users user : usersList) {
            if (user.getId().equals(userId)) {
                usersList.remove(user);
                break;
            }
        }
        return usersList;
    }



    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<Users> createUser(@RequestBody Users newuser) {
        usersList.add(newuser);
        return ResponseEntity.status(201).body(newuser);
    }


    @PutMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<Users> updateUser(@PathVariable("id") String userId, @RequestBody Users updateUser) {
        for (Users user : usersList) {
            if (user.getId().equals(userId)) {
                user.setName(updateUser.getName());
                user.setEmail(updateUser.getEmail());


                return ResponseEntity.status(200).body(user);
            }
        }


        return ResponseEntity.status(404).body(null);
    }
}
