package org.dnyanyog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
    
    @GetMapping("/api/v1/vaibhav")
    public String greetingsVaibhav() {
	System.out.println("Get Request called at /api/v1/vaibhav" ); 
	return "Hello Vaibhav !!";
    }
    
    @GetMapping("/")
    public String greetings() {
	System.out.println("Get Request called at /" ); 
	return "Hello world !!";
    }   
}