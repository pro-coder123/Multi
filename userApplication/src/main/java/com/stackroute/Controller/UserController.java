package com.stackroute.Controller;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/api")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        ResponseEntity responseEntity;
        try{
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>("Fail",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("users")
    public ResponseEntity<?> getUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
    }
}
