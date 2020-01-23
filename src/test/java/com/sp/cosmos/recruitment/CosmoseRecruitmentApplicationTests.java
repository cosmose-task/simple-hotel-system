package com.sp.cosmos.recruitment;

import com.sp.cosmos.recruitment.model.Customer;
import com.sp.cosmos.recruitment.model.Reservation;
import com.sp.cosmos.recruitment.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CosmoseRecruitmentApplicationTests {
	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	@Test
	public void getCustomerReservation() throws Exception {
		ResponseEntity<Reservation[]> response = restTemplate.getForEntity(
				createURLWithPort("/reservations/-1"), Reservation[].class);
		assertEquals(5, response.getBody().length);
	}

	@Test
	public void testGetCustomerReservation() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("firstName");
		customer.setLastName("lastName");
		HttpEntity entity = new HttpEntity<>(customer, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(
				createURLWithPort("/customer"), entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertTrue(actual.contains("/customer"));
		response.getBody();
	}

	@Test
	public void testCancelReservation() throws Exception {
		restTemplate.delete(createURLWithPort("/reservations/-1/-1"));

		ResponseEntity<Reservation[]> responseAfterCancel = restTemplate.getForEntity(
				createURLWithPort("/reservations/-1"), Reservation[].class);
		assertEquals(4, responseAfterCancel.getBody().length);
	}

	@Test
	public void testGetRooms() throws Exception {
		ResponseEntity<Room[]> response = restTemplate.getForEntity(
				createURLWithPort("/rooms?startDate=2020-03-05&endDate=2020-03-15&startRange=200&endRange=250&city=Warsaw"),
				Room[].class);

		assertEquals(1, response.getBody().length);
	}

	@Test
	public void testAddReservation() throws Exception {
		Reservation reservation = new Reservation();
		reservation.setStartTime(LocalDate.of(2020, 4, 28));
		reservation.setEndTime(LocalDate.of(2020, 4, 30));
		Room room = new Room();
		room.setId(-1L);
		reservation.setRoom(room);

		HttpEntity entity = new HttpEntity<>(reservation, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(
				createURLWithPort("/reservations/-1"), entity,
				String.class);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testAddReservation_badRequest() throws Exception {
		Reservation reservation = new Reservation();
		reservation.setStartTime(LocalDate.of(2020, 4, 8));
		reservation.setEndTime(LocalDate.of(2020, 4, 30));
		HttpEntity entity = new HttpEntity<>(reservation, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(
				createURLWithPort("/reservations/-1/-1"), entity,
				String.class);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}