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
import java.time.format.DateTimeFormatter;
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
import com.safetynet.alerts.model.Person;

@WebMvcTest(MedicalRecordDaoImpl.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class MicroServicePersonTest {

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
	@Tag("SuccessfulRequest")
	void givenListPerson_whenGet_thenReturnList() throws Exception {
		mockMvc.perform(get("/person")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(23))).andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].lastName", is("Boyd"))).andExpect(jsonPath("$[13].firstName", is("Zach")))
				.andExpect(jsonPath("$[13].lastName", is("Zemicks")));
	}

	@Order(2)
	@Test
	@Tag("SuccessfulRequest")
	void test_post_put_delete_person_success() throws Exception {
		Person person = new Person("Julien", "Test", "address", "Somewhere","69122","0684949494","julientest@somewhere.com",null,null);
		String medicalrecordString = asString(person);
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(medicalrecordString))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost/person/")));
		mockMvc.perform(get("/person")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(24))).andExpect(jsonPath("$[23].firstName", is("Julien")))
				.andExpect(jsonPath("$[23].address", is("address")));
		person = new Person("Julien", "Test", "other address", "Somewhere","69122","0684949494","julientest@somewhere.com",null,null);
		medicalrecordString = asString(person);
		mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(medicalrecordString))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/person")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(24))).andExpect(jsonPath("$[23].address",
						is("other address")));
		person = new Person("Julien", "Test", null, null, null,null,null,null,null);
		medicalrecordString = asString(person);
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON).content(medicalrecordString))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/person")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(23))).andExpect(jsonPath("$[22].firstName", is("Eric")))
				.andExpect(jsonPath("$[22].lastName", is("Cadigan")));
	}

	@Order(3)
	@Test
	void test_create_person_failure() throws Exception {
		Person person = null;
		mockMvc.perform(
				post("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isBadRequest());
	}

	@Order(4)
	@Test
	void test_update_person_failure() throws Exception {
		Person person = null;
		mockMvc.perform(
				put("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isBadRequest());
	}

	@Order(5)
	@Test
	void test_delete_person_failure() throws Exception {
		Person person = null;
		mockMvc.perform(
				delete("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isBadRequest());
	}

	@Order(6)
	@Test
	void test_update_person_failure_NotFoundItem() throws Exception {
		Person person = new Person("Julien", "Test", "address", "city", "zip","841-874","email@email.com",null,null);
		mockMvc.perform(
				put("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().string("The person was not updated"));
	}

	@Order(7)
	@Test
	void test_delete_person_failure_NotFoundItem() throws Exception {
		Person person = new Person("Julien", "Test", null, null, null,null,null,null,null);
		mockMvc.perform(
				delete("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().string("The person was not found"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String asString(final Person person) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String asString(final LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		return date.format(formatter);
	}
}
