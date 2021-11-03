package com.operations.request;

import lombok.Data;

@Data
public class UserRequest {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
}
