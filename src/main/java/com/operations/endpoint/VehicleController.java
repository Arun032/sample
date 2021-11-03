package com.operations.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.operations.request.VehicleRequest;
import com.operations.response.VehicleResponse;
import com.operations.service.VehicleService;

@RequestMapping("/v1")
@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	public String addVehicle(@RequestBody VehicleRequest vehicleRequest) {
		return vehicleService.addVehicle(vehicleRequest);
	}

	@RequestMapping(value = "/vehicle/list", method = RequestMethod.GET)
	public List<VehicleResponse> getVehicleList() {
		return vehicleService.getVehicleList();
	}
}
