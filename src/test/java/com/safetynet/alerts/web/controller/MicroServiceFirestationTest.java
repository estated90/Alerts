package com.safetynet.alerts.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.filter.CORSFilter;
import com.safetynet.alerts.interfaces.IFirestationDao;
import com.safetynet.alerts.model.Firestation;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MicroServiceFirestation.class)
class MicroServiceFirestationTest {

	private MockMvc mockMvc;
	@Mock
	private static IFirestationDao firestastionDao;
	@InjectMocks
	private static MicroServiceFirestation microServiceFirestation;

	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(microServiceFirestation).addFilters(new CORSFilter()).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void whenValidGet_thenPortListened() throws Exception {
		List<Firestation> firestation = Arrays.asList(new Firestation("1509 Culver St", 3),
				new Firestation("29 15th St", 2));
		when(firestastionDao.returnAllFirestation()).thenReturn(firestation);
		mockMvc.perform(get("/firestation")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].station", is(3))).andExpect(jsonPath("$[0].address", is("1509 Culver St")))
				.andExpect(jsonPath("$[1].station", is(2))).andExpect(jsonPath("$[1].address", is("29 15th St")));
		verify(firestastionDao, times(1)).returnAllFirestation();
		verifyNoMoreInteractions(firestastionDao);
	}

	@Test
	public void test_create_firestation_failure() throws Exception {
		Firestation firestation = new Firestation("1509 Culver St", 5);
		when(firestastionDao.saveFirestation(firestation)).thenReturn(null);
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isNoContent());
	}

	@Test
	public void test_create_firestation_success() throws Exception {
		Firestation firestation = new Firestation("address test", 5);
	    when(firestastionDao.saveFirestation(firestation)).thenReturn(firestation);
	    mockMvc.perform(
	            post("/firestation")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(firestation)))
	            .andExpect(status().isCreated())
	            .andExpect(header().string("location", containsString("http://localhost/firestation/")));
	    verify(firestastionDao, times(1)).saveFirestation(firestation);
	    verifyNoMoreInteractions(firestastionDao);
	}
	@Test
	public void test_update_firestation_success() throws Exception {
		Firestation firestation = new Firestation("1509 Culver St", 5);
	    when(firestastionDao.updateFirestation(firestation)).thenReturn(firestation);
	    mockMvc.perform(
	            put("/firestation")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(firestation)))
	            .andExpect(status().isOk());
	    verify(firestastionDao, times(1)).updateFirestation(firestation);
	    verifyNoMoreInteractions(firestation);
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
