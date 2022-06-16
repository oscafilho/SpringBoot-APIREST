package com.icaetano.login.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World!";
	}
	
	@RequestMapping("/ola")
	@ResponseBody
	public String Ola() {
		return "Olá";
	}
	
}
