package com.keduit.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Blank {
	
	@GetMapping
	public String user() {
		return "get users";
	}

	@PostMapping
	public String addUser() {
		return "add user";
	}

	@PutMapping("/{userId}")
	public String findUser(@PathVariable String userId) {
		return "get userId= " + userId;
	}

	@PatchMapping("/{userId}")
	public String updateUser(@PathVariable String userId) {
		return "update userId= " + userId;
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable String userId) {
		return "delete userId= " + userId;
	}
}
