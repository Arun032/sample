//package com.operations;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.operations.request.VehicleRequest;
//
//@SpringBootTest
//public class AppTest {
//
//	@Test
//	public void testGetVehicleListSuccess() throws URISyntaxException {
//
//		RestTemplate restTemplate = new RestTemplate();
//
//		String baseUrl = "http://localhost:" + 8089 + "/v1/vehicle/list";
//
//		System.out.println("The Base URL " + baseUrl);
//
//		URI uri = new URI(baseUrl);
//
//		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//		System.out.println("The status code " + result.getStatusCodeValue());
//
//		Assert.assertEquals(200, result.getStatusCodeValue());
//
//	}
//
//	@Test
//	public void testAddVehicleSuccess() throws URISyntaxException {
//
//		RestTemplate restTemplate = new RestTemplate();
//
//		String baseUrl = "http://localhost:" + 8089 + "/v1/vehicle";
//
//		System.out.println("The Base URL " + baseUrl);
//
//		URI uri = new URI(baseUrl);
//
//		VehicleRequest vehicleRequest = new VehicleRequest();
//		vehicleRequest.setId(3L);
//		vehicleRequest.setVehicleId("ghi");
//		vehicleRequest.setVehicleType("ghi");
//		vehicleRequest.setVehicleNo("ghi");
//
//		ResponseEntity<String> result = restTemplate.postForEntity(uri, vehicleRequest, String.class);
//
//		System.out.println("The status code " + result.getStatusCodeValue());
//
//		Assert.assertEquals(200, result.getStatusCodeValue());
//
//	}
//
//}
