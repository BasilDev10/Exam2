package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.User;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserContoller {


    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUser(){

        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/get-by-balance/{balance}")
    public ResponseEntity getUserByBalance(@PathVariable int balance){

        return ResponseEntity.ok(userService.getUserByBalance(balance));
    }
    @GetMapping("/get-by-age/{age}")
    public ResponseEntity getUserByAge(@PathVariable int age){

        return ResponseEntity.ok(userService.getUserByAge(age));
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        userService.userAdd(user);
        return ResponseEntity.status(201).body(new ApiResponse("user is added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        if(userService.userUpdate( id,user))return ResponseEntity.ok(new ApiResponse("user is updated "));
        else return ResponseEntity.status(400).body(new ApiResponse("user not found"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if(userService.userDelete( id))return ResponseEntity.ok(new ApiResponse("user is deleted "));
        else return ResponseEntity.status(400).body(new ApiResponse("user not found"));
    }


}
