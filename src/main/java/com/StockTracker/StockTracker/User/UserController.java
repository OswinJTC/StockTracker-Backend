package com.StockTracker.StockTracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "https://stock-tracker-vt1d.onrender.com")
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User retrievedUser = userService.findUserByUsername(user.getUsername());
        if (retrievedUser != null && userService.checkPassword(retrievedUser, user.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }






}
