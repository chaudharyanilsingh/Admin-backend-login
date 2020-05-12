package com.testing.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testing.common.Response;
import com.testing.model.Admin;
import com.testing.model.ApiResponse;
import com.testing.model.User;
import com.testing.service.AdminService;
import com.testing.service.UserService;
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;
	
	@GetMapping(value="{id}")
	public Admin getAllAdmins(@PathVariable(value="id") Long id){
		return adminService.getAdminById(id);

	}
	@GetMapping(value="admin/changeStatus")
	public void changeStatus(@RequestParam(name="id")Long id,@RequestParam(name="status") Boolean status) {
		User user=userService.getUserById(id);
		user.setStatus(!status);
		userService.saveUser(user);
		
	}
	
}
