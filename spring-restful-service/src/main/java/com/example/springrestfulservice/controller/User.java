package com.example.springrestfulservice.controller;

import lombok.*;

//this class is made for response
//responsestatus depends on method level
//lets create pojo class below
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
private int id;
private String name;
}
