//package com.operations;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//
//import java.util.List;
//
//import org.hamcrest.collection.IsEmptyCollection;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.operations.request.UserRequest;
//import com.operations.request.VehicleRequest;
//import com.operations.response.UserResponse;
//import com.operations.response.VehicleResponse;
//import com.operations.service.UserService;
//import com.operations.service.VehicleService;
//
//@SpringBootTest
//public class VehicleServiceTest {
//
//	@Autowired
//	private VehicleService vehicleService;
//
//	@Autowired
//	private UserService userService;
//
//	@Test
//	public void addVehicleTest() {
//		VehicleRequest vehicleRequest = new VehicleRequest();
//		vehicleRequest.setId(3L);
//		vehicleRequest.setVehicleId("ghi");
//		vehicleRequest.setVehicleType("ghi");
//		vehicleRequest.setVehicleNo("ghi");
//		String response = vehicleService.addVehicle(vehicleRequest);
//		assertEquals("Success", response);
//	}
//
//	@Test
//	public void getVehicleListTest() {
//		List<VehicleResponse> response = vehicleService.getVehicleList();
//		assertThat(response, IsEmptyCollection.empty());
//	}
//
//	@Test
//	public void addUserTest() {
//		UserRequest userRequest = new UserRequest();
//		userRequest.setId(3L);
//		userRequest.setEmail("smileforsantosh@gmail.com");
//		userRequest.setFirstName("santosh");
//		userRequest.setLastName("miriyala");
//		userRequest.setMobile("9966171071");
//		String response = userService.addUser(userRequest);
//		assertEquals("Success", response);
//	}
//
//	@Test
//	public void getUserListTest() {
//		List<UserResponse> response = userService.getUserList();
//		assertThat(response, IsEmptyCollection.empty());
//	}
//}
