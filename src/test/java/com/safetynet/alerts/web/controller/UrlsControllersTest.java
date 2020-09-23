package com.safetynet.alerts.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecords;
import com.safetynet.alerts.model.Person;

@WebMvcTest(UrlsControllers.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class UrlsControllersTest {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ListObjects listObjects;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Order(1)
	@Tag("Success")
	void firestationListPerson_return_child_Adult_Success() throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.person").isArray()).andExpect(jsonPath("$.person", hasSize(6)))
				.andExpect(jsonPath("$.numberOfAdult", is(5))).andExpect(jsonPath("$.numberOfChildren", is(1)));
	}

	@Test
	@Order(2)
	@Tag("Fail")
	void firestationListPerson_return_child_Adult_Failure() throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "6")).andExpect(status().isNoContent());
	}

	@Test
	@Order(3)
	@Tag("Fail")
	void firestationListPerson_NoContent_Failure() throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "")).andExpect(status().isBadRequest());
	}

	@Test
	@Order(4)
	@Tag("Success")
	void childAlert_return_child_family_Success() throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "1509 Culver St")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.child").isArray()).andExpect(jsonPath("$.child", hasSize(2)))
				.andExpect(jsonPath("$.familyMember", hasSize(3)))
				.andExpect(jsonPath("$.child[0].firstName", is("Tenley"))).andExpect(jsonPath("$.child[1].age", is(3)));
	}

	@Test
	@Order(5)
	@Tag("Fail")
	void childAlert_return_child_family_empty() throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "test")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.child").isArray()).andExpect(jsonPath("$.child", hasSize(0)))
				.andExpect(jsonPath("$.familyMember", hasSize(0)));
	}

	@Test
	@Order(6)
	@Tag("Success")
	void phoneAlert_return_phone_Success() throws Exception {
		mockMvc.perform(get("/phoneAlert").param("firestation", "1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.phoneNumber").isArray()).andExpect(jsonPath("$.phoneNumber", hasSize(6)))
				.andExpect(jsonPath("$.phoneNumber[0]", is("841-874-6512")))
				.andExpect(jsonPath("$.phoneNumber[5]", is("841-874-7784")));
	}

	@Test
	@Order(7)
	@Tag("Fail")
	void phoneAlert_return_phone_fail() throws Exception {
		mockMvc.perform(get("/phoneAlert").param("firestation", "8"))
				.andExpect(status().isNoContent());
	}

	@Test
	@Order(8)
	@Tag("Success")
	void fire_return_fireAddress_Success() throws Exception {
		mockMvc.perform(get("/fire").param("address", "1509 Culver St"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.inhabitant").isArray())
				.andExpect(jsonPath("$.inhabitant", hasSize(5)))
				.andExpect(jsonPath("$.station", is(3)))
				.andExpect(jsonPath("$.inhabitant[4].age", is(34)))
				.andExpect(jsonPath("$.inhabitant[3].medications").isArray())
				.andExpect(jsonPath("$.inhabitant[1].medications", hasSize(3)));
	}

	@Test
	@Order(9)
	@Tag("Fail")
	void fire_return_fireAddress_fail() throws Exception {
		mockMvc.perform(get("/fire").param("address", "test address")).andExpect(status().isNoContent());
	}

	@Test
	@Order(10)
	@Tag("Success")
	void flood_return_listFlood_Success() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[1].address", is("908 73rd St")))
				.andExpect(jsonPath("$[2].inhabitant").isArray())
				.andExpect(jsonPath("$[2].inhabitant[0].age", is(45)))
				.andExpect(jsonPath("$[2].inhabitant[0].medications").isArray());
	}

	@Test
	@Order(11)
	@Tag("Fail")
	void flood_return_listFlood_fail() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "8")).andExpect(status().isNoContent());
	}

	@Test
	@Order(12)
	@Tag("Success")
	void flood_multiple_stations_return_listFlood_Success() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "1,2")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(6)));
	}

	@Test
	@Order(13)
	@Tag("Success")
	void flood_multiple_stations_one_unknown_return_listFlood_Success() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "1,8"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(3)));
	}

	@Test
	@Order(14)
	@Tag("Success")
	void flood_multiple_stations_all_unknown_return_listFlood_Success() throws Exception {
		mockMvc.perform(get("/flood/stations").param("stations", "7,8"))
			.andExpect(status().isNoContent());
	}
	@Test
	@Order(15)
	@Tag("Success")
	void personInfo_return_list_personInfo_Success() throws Exception {
		mockMvc.perform(get("/personInfo").param("firstName", "Allison").param("lastName", "Boyd"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", is("Allison")))
				.andExpect(jsonPath("$[0].medication").isArray())
				.andExpect(jsonPath("$[0].age", is(54)));
	}
	@Test
	@Order(16)
	@Tag("Fail")
	void personInfo_return_list_personInfo_fail() throws Exception {
		mockMvc.perform(get("/personInfo").param("firstName", "test").param("lastName", "Boyd"))
				.andExpect(status().isNoContent());	
	}
	@Test
	@Order(17)
	@Tag("Fail")
	void personInfo_return_list_personInfo_wronglastName_fail() throws Exception {
		mockMvc.perform(get("/personInfo")
				.param("firstName", "Allison").param("lastName", "test"))
				.andExpect(status().isNoContent());	
	}
	@Test
	@Order(18)
	@Tag("Success")
	void personInfo_return_list_personInfo_homonym_Success() throws Exception {
		List<String> medicationsList = new ArrayList<>();
		List<String> allergiesList = new ArrayList<>();
		Medicalrecords medicalrecord = new Medicalrecords("Allison", "Boyd", LocalDate.of(1990, 9, 30), medicationsList, allergiesList);
		Person person = new Person("Allison", "Boyd", "address", "Somewhere","69122","0684949494","julientest@somewhere.com",null,medicalrecord);
		listObjects.getPersons().add(person);
		mockMvc.perform(get("/personInfo").param("firstName", "Allison").param("lastName", "Boyd"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(2)));
	}
	@Test
	@Order(19)
	@Tag("Success")
	void communityEmail_return_list_email_Success() throws Exception {
		mockMvc.perform(get("/communityEmail").param("city", "Culver"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.email").isArray())
				.andExpect(jsonPath("$.email", hasSize(23)));
	}
	@Test
	@Order(20)
	@Tag("Fail")
	void communityEmail_return_list_email_fail() throws Exception {
		mockMvc.perform(get("/communityEmail").param("city", "Lyon"))
				.andExpect(status().isNoContent());	
	}
}
