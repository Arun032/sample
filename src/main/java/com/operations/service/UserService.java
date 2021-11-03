package com.operations.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.operations.constant.NamedQueryEnum;
import com.operations.domain.User;
import com.operations.request.UserRequest;
import com.operations.response.UserResponse;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private ModelMapper modelMapper;

	public String addUser(UserRequest userRequest) {
		User user = modelMapper.map(userRequest, User.class);
		persistenceService.save(user);
		return "Success";
	}

	public List<User> fetchUserList() {
		Map vehiclesMap = new HashMap<>();
		return persistenceService.getList(NamedQueryEnum.GET_USERS, vehiclesMap);
	}

	public List<UserResponse> getUserList() {
		List<User> userList = fetchUserList();

		List<UserResponse> users = new ArrayList<>();

		for (User user : userList) {
			UserResponse userResponse = modelMapper.map(user, UserResponse.class);
			users.add(userResponse);
		}

		return users;
	}

}
