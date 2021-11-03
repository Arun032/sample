package com.operations.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.operations.constant.NamedQuery;
import com.operations.constant.NamedQueryEnum;
import com.operations.domain.Vehicle;
import com.operations.request.VehicleRequest;
import com.operations.response.VehicleResponse;

@Service
@Transactional(readOnly = true)
public class VehicleService {

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = false)
	public String addVehicle(VehicleRequest vehicleRequest) {

		Vehicle vehicle = modelMapper.map(vehicleRequest, Vehicle.class);

		persistenceService.save(vehicle);
		return "Success";
	}

	public List<Vehicle> fetchVehiclesList() {
		Map vehiclesMap = new HashMap<>();
		return persistenceService.getList(NamedQueryEnum.GET_VEHICLES, vehiclesMap);
	}

	public List<VehicleResponse> getVehicleList() {
		List<Vehicle> vehicleList = fetchVehiclesList();

		List<VehicleResponse> vehicles = new ArrayList<>();

		for (Vehicle vehicle : vehicleList) {
			VehicleResponse vehicleResponse = modelMapper.map(vehicle, VehicleResponse.class);
			vehicles.add(vehicleResponse);
		}

		return vehicles;
	}
}
