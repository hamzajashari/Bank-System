package com.bank.Controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class BankSystemController{

    private static final String template = "Hello, %s!";

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}

}