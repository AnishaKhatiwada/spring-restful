package com.example.springrestfulservice.controller;


import lombok.*;

//This is DTO class
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ErrorResponse {
private boolean success;
private String message;
}
