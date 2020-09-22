package com.safetynet.alerts.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@WebMvcTest(UrlsControllers.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class UrlsControllersTest {

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	@Order(1)
	@Tag("Success")
	void firestationListPerson_return_child_Adult_Success () throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.person").isArray())
		.andExpect(jsonPath("$.person", hasSize(6)))
		.andExpect(jsonPath("$.numberOfAdult", is(5)))
		.andExpect(jsonPath("$.numberOfChildren", is(1)));
	}
	@Test
	@Order(2)
	@Tag("Fail")
	void firestationListPerson_return_child_Adult_Failure () throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", "6"))
		.andExpect(status().isNoContent());
	}
	@Test
	@Order(3)
	@Tag("Fail")
	void firestationListPerson_NoContent_Failure () throws Exception {
		mockMvc.perform(get("/firestation").param("stationNumber", ""))
		.andExpect(status().isBadRequest());
	}
	@Test
	@Order(4)
	@Tag("Success")
	void childAlert_return_child_family_Success () throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "1509 Culver St"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.child").isArray())
		.andExpect(jsonPath("$.child", hasSize(2)))
		.andExpect(jsonPath("$.familyMember", hasSize(3)))
		.andExpect(jsonPath("$.child[0].firstName", is("Tenley")))
		.andExpect(jsonPath("$.child[1].age", is(3)));
	}
	@Test
	@Order(5)
	@Tag("Fail")
	void childAlert_return_child_family_empty () throws Exception {
		mockMvc.perform(get("/childAlert").param("address", "test"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.child").isArray())
		.andExpect(jsonPath("$.child", hasSize(0)))
		.andExpect(jsonPath("$.familyMember", hasSize(0)));
	}
}
