package com.safetynet.alerts.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.dao.FirestationDaoImpl;
//import com.safetynet.alerts.filter.CORSFilter;
import com.safetynet.alerts.model.Firestation;

@WebMvcTest(FirestationDaoImpl.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class MicroServiceFirestationTest {

	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private FirestationDaoImpl firestastionDao;
	@InjectMocks
	private MicroServiceFirestation microServiceFirestation;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		// mockMvc =
		// MockMvcBuilders.standaloneSetup(microServiceFirestation).addFilters(new
		// CORSFilter()).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	@Tag("SuccessfulRequest")
	void givenListFirestation_whenGet_thenReturnList() throws Exception {
		mockMvc.perform(get("/firestation")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(13))).andExpect(jsonPath("$[0].station", is(3)))
				.andExpect(jsonPath("$[0].address", is("1509 Culver St"))).andExpect(jsonPath("$[1].station", is(2)))
				.andExpect(jsonPath("$[1].address", is("29 15th St")));
	}

	@Order(2)
	@Test
	@Tag("SuccessfulRequest")
	void test_post_put_delete_firestation_success() throws Exception {
		Firestation firestation = new Firestation("address test", 5);
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost/firestation/")));
		mockMvc.perform(get("/firestation")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(14))).andExpect(jsonPath("$[13].station", is(5)))
				.andExpect(jsonPath("$[13].address", is("address test")));
		firestation = new Firestation("address test", 6);
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/firestation")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(14))).andExpect(jsonPath("$[13].station", is(6)))
				.andExpect(jsonPath("$[13].address", is("address test")));
		firestation = new Firestation(null, 6);
		mockMvc.perform(
				delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/firestation")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(13))).andExpect(jsonPath("$[12].station", is(2)))
				.andExpect(jsonPath("$[12].address", is("951 LoneTree Rd")));
		firestation = new Firestation("address test", 6);
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost/firestation/")));
		firestation = new Firestation("address test", 0);
		mockMvc.perform(
				delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/firestation")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(13))).andExpect(jsonPath("$[12].station", is(2)))
				.andExpect(jsonPath("$[12].address", is("951 LoneTree Rd")));
		firestation = new Firestation("address test", 6);
	}

	@Order(3)
	@Test
	void test_create_firestation_failure() throws Exception {
		Firestation firestation = null;
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isBadRequest());
	}
	
	@Order(4)
	@Test
	void test_update_firestation_failure() throws Exception {
		Firestation firestation = null;
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isBadRequest());
	}
	@Order(5)
	@Test
	void test_delete_firestation_failure() throws Exception {
		Firestation firestation = null;
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isBadRequest());
	}
	@Order(6)
	@Test
	void test_update_firestation_failure_NotFoundItem() throws Exception {
		Firestation firestation = new Firestation("Testing address", 7);
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isUnprocessableEntity()).andExpect(content().string("Address was unknown"));
	}
	@Order(7)
	@Test
	void test_delete_firestation_failure_NotFoundItem() throws Exception {
		Firestation firestation = new Firestation("Testing address", 7);
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isUnprocessableEntity()).andExpect(content().string("Address and Id were unknown"));
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
