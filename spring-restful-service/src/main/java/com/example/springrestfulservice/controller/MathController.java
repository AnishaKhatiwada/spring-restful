package com.example.springrestfulservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//example of restful where responsebody, mapping, controller is used
@Slf4j
@RestController
@RequestMapping
public class MathController {

    //    @GetMapping
    @GetMapping(value = "math/addition")
    public ResponseEntity<Integer> add() {
        int sum = 1 + 2;
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @PostMapping(value = "math/doadd")
    public int doAdd(@RequestBody Operand operand) {
        int sum = operand.getNum1() + operand.getNum2();
        return sum;
    }

    @RequestMapping(value = "math/greet", method = RequestMethod.GET)
    public String greeting() {
        return "Welcome";
    }


    //using requestparam
//{base_URI}/math/greet1?name=Anisha
    @RequestMapping(value = "math/greet1", method = RequestMethod.GET)
    public String greeting(@RequestParam(name = "name", defaultValue = "Anisha") String name) {
        return "Welcome" + name;
    }

    @RequestMapping(value = "diff/{num1}/{num2}", method = RequestMethod.GET)
    public int sub(@PathVariable int num1, @PathVariable int num2) {
        log.info("Path variables : {},{}", num1, num2);
        int difference = num1 - num2;
        return difference;
    }

    //wiriting either name or value is same thing inside pathvariable
    //Benefits of writing name:  In Operation writing num1 num2 is not compulsory, you can use any other variable
    @RequestMapping(value = {"mul", "mul/{num2}"}, method = RequestMethod.GET)
    public int mul(@PathVariable(value = "num2", required = false) Integer b) {
        int a = 7;
        log.info("Path variables : num1 {},num2 {}", a, b);
        int m = a * b;
        return m;
    }


    //http://localhost:8080/operand
    //lets make  a method
    @RequestMapping(value = "operand", method = RequestMethod.GET)
    public ResponseEntity<List<Operand>> getOperand() {
        Operand operand = new Operand();
        operand.setNum1(3);
        operand.setNum2(5);
        List<Operand> list = new ArrayList<>();
        list.add(operand);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //using exception handler
    @GetMapping("employee/salary")
    public String getSalary() {
        String name = null;
        name.toUpperCase();
        return String.valueOf(50000);
    }

    //    @ExceptionHandler
    // public String handleError(NullPointerException e){
    // return "Null Pointer exception is occured.";

    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleError(NullPointerException e) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Internal server error");
        log.error("Error occured: {}", errorResponse);
        return errorResponse;
    }


    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public ErrorResponse handleError(StringIndexOutOfBoundsException e) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Internal server error");
        log.error("Error occured: {}", errorResponse);
        return errorResponse;
    }
}