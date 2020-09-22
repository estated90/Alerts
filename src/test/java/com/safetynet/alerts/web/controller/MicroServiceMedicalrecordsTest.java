package com.safetynet.alerts.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.alerts.model.Medicalrecord;

@WebMvcTest(MedicalRecordDaoImpl.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class MicroServiceMedicalrecordsTest {

	@Autowired
	private MockMvc mockMvc;

	private static List<String> medicationsList;
	private static List<String> allergiesList;
	private static LocalDate birthdate = LocalDate.now().minusYears(30);
	static {
		medicationsList = new ArrayList<>();
		medicationsList.add("medication1");

		allergiesList = new ArrayList<>();
		allergiesList.add("allergies");
	}

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
		mockMvc.perform(get("/medicalrecord")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(23))).andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].lastName", is("Boyd"))).andExpect(jsonPath("$[13].firstName", is("Zach")))
				.andExpect(jsonPath("$[13].lastName", is("Zemicks")));
	}

	@Order(2)
	@Test
	@Tag("SuccessfulRequest")
	void test_post_put_delete_firestation_success() throws Exception {
		Medicalrecord medicalrecord = new Medicalrecord("Julien", "Test", birthdate, medicationsList, allergiesList);
		String medicalrecordString = asString(medicalrecord);
		mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalrecordString))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost/medicalrecord/")));
		mockMvc.perform(get("/medicalrecord")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(24))).andExpect(jsonPath("$[23].firstName", is("Julien")))
				.andExpect(jsonPath("$[23].lastName", is("Test")));
		medicalrecord = new Medicalrecord("Julien", "Test", LocalDate.now().minusYears(20), null, null);
		medicalrecordString = asString(medicalrecord);
		mockMvc.perform(
				put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalrecordString))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/medicalrecord")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(24)))
				.andExpect(jsonPath("$[23].birthdate", is(asString(LocalDate.now().minusYears(20)))));
		medicalrecord = new Medicalrecord("Julien", "Test", null, null, null);
		medicalrecordString = asString(medicalrecord);
		mockMvc.perform(
				delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(medicalrecordString))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/medicalrecord")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(23))).andExpect(jsonPath("$[22].firstName", is("Eric")))
				.andExpect(jsonPath("$[22].lastName", is("Cadigan")));
	}

	@Order(3)
	@Test
	void test_create_firestation_failure() throws Exception {
		Medicalrecord medicalrecord = null;
		mockMvc.perform(
				post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(asJsonString(medicalrecord)))
				.andExpect(status().isBadRequest());
	}

	@Order(4)
	@Test
	void test_update_firestation_failure() throws Exception {
		Medicalrecord medicalrecord = null;
		mockMvc.perform(
				put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(asJsonString(medicalrecord)))
				.andExpect(status().isBadRequest());
	}

	@Order(5)
	@Test
	void test_delete_firestation_failure() throws Exception {
		Medicalrecord medicalrecord = null;
		mockMvc.perform(
				delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(asJsonString(medicalrecord)))
				.andExpect(status().isBadRequest());
	}

	@Order(6)
	@Test
	void test_update_firestation_failure_NotFoundItem() throws Exception {
		Medicalrecord medicalrecord = new Medicalrecord("Julien", "Test", null, null, null);
		mockMvc.perform(
				put("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(asJsonString(medicalrecord)))
				.andExpect(status().isUnprocessableEntity()).andExpect(content().string("Address was unknown"));
	}

	@Order(7)
	@Test
	void test_delete_firestation_failure_NotFoundItem() throws Exception {
		Medicalrecord firestation = new Medicalrecord("Julien", "Test", null, null, null);
		mockMvc.perform(
				delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestation)))
				.andExpect(status().isUnprocessableEntity()).andExpect(content().string("Address and Id were unknown"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String asString(final Medicalrecord medicalrecord) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(medicalrecord);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	public static String asString(final LocalDate date) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(date);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
