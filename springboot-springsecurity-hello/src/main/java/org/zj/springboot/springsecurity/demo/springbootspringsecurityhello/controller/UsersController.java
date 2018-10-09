package org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.model.Users;
import org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.repository.UsersRepository;

@RestController
@RequestMapping("/user/")
public class UsersController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping("query/{id}")
	public String queryUserById(@PathVariable("id") Long id) {
		
		Optional<Users> optionalUsers = usersRepository.findById(id);
		
		optionalUsers.orElseThrow(()->new RuntimeException("没有该用户"));
		
		return optionalUsers.get().getUserName();
	}

}
