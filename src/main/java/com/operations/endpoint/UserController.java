package com.operations.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.operations.request.UserRequest;
import com.operations.response.UserResponse;
import com.operations.service.UserService;

@RequestMapping("/v1")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(@RequestBody UserRequest userRequest) {
		return userService.addUser(userRequest);
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public List<UserResponse> getUserList() {
		return userService.getUserList();
	}
}
