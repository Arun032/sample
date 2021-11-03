package com.operations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.operations.request.VehicleRequest;
import com.operations.service.VehicleService;

@Component
public class VehicleServiceTest {

	@Autowired
	private VehicleService vehicleService;

	@Test
	public void addUserTest() {
		VehicleRequest vehicleRequest = new VehicleRequest();
		vehicleRequest.setId(3L);
		vehicleRequest.setVehicleId("ghi");
		vehicleRequest.setVehicleType("ghi");
		vehicleRequest.setVehicleNo("ghi");
		String response = vehicleService.addVehicle(vehicleRequest);
		assertEquals("Success", response);
	}
}
