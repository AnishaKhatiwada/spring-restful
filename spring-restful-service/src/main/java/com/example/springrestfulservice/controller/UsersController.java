package com.example.springrestfulservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "users")
public class UsersController {


   @GetMapping

    public ResponseEntity<List<String>> getAllUsers() {
        String str = null;
        str.toUpperCase();
        return new ResponseEntity<>(Arrays.asList("a", "b", "c"),HttpStatus.OK);
    }

    //short example to show use of responsestatus
    @PostMapping
   // @ResponseStatus(HttpStatus.CREATED)
    //instead of writing responsestatus annotation, we can use response entity
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(4);

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", String.valueOf(user.getId()));
        //return new ResponseEntity<>(user,  HttpStatus.CREATED);
//after adding token
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleError(NullPointerException e) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Internal server error");
        log.error("Error occured: {}", errorResponse);
        return errorResponse;
    }
}