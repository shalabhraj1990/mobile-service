package com.spring.boot.mobile.integration;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import msk.spring.boot.common.dto.Response;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MobileServiceIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@WithMockUser(username = "mobile-user", password = "mobile-user", roles = "MOBILE_USER")
	@Test
	@Order(value = 1)
	public void testGetMobileById() throws Exception {
		mvc.perform(get("/mobiles/1").accept(MediaType.APPLICATION_JSON)

		).andExpect(status().is(202))
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.id").value(1)).andExpect(jsonPath("$.data.name").value("NOKIA"))
				.andExpect(jsonPath("$.data.price").value(10000.0));
	}

	@WithMockUser(username = "mobile-user", password = "mobile-user", roles = "MOBILE_USER")
	@Test
	@Order(value = 2)
	public void testGetMobileByIdWithError() throws Exception {
		mvc.perform(get("/mobiles/1111").accept(MediaType.APPLICATION_JSON)

		).andExpect(status().is(400))
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errors.code").value(1001))
				.andExpect(jsonPath("$.errors.message").value("mobile id not found !!!!"));
	}

	@WithMockUser(username = "mobile-user", password = "mobile-user", roles = "MOBILE_USER")
	@Test
	@Order(value = 3)
	public void testGetMobile() throws Exception {
		mvc.perform(get("/mobiles").accept(MediaType.APPLICATION_JSON)

		).andExpect(status().is(202))
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(8))).andExpect(jsonPath("$.data[1].id").value(2))
				.andExpect(jsonPath("$.data[1].name").value("SAMSUNG-XLS"))
				.andExpect(jsonPath("$.data[1].price").value(10000.0));
	}

	@WithMockUser(username = "mobile-user", password = "mobile-user", roles = "MOBILE_USER")
	@Test
	@Order(value = 4)
	public void testSaveMobile() throws Exception {
		SaveMobileDto saveMobile = new SaveMobileDto();
		saveMobile.setName("iphone 13+");
		saveMobile.setStatus(String.valueOf(msk.spring.boot.common.mobile.dto.Status.AVAILABLE));
		saveMobile.setLineOfBussiness("ONLINE");
		saveMobile.setCountryCode("US");
		saveMobile.setPrice(10000.0);
		saveMobile.setAccessoryType("ALL");

		String jsonString = new ObjectMapper().writeValueAsString(saveMobile);
		mvc.perform(post("/mobiles").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().is(202)).andExpect(jsonPath("$.data", hasSize(9)));
	}

	@WithMockUser(username = "mobile-user", password = "mobile-user", roles = "MOBILE_USER")
	@Test
	@Order(value = 5)
	public void testDeleteById() throws Exception {
		MvcResult result = mvc.perform(get("/mobiles").accept(MediaType.APPLICATION_JSON)

		).andReturn();

		String json = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Response<List<MobileDto>> respone = mapper.readValue(json, new TypeReference<Response<List<MobileDto>>>() {
		});
		System.out.println(respone.getData().size());
		MobileDto lastMobile = respone.getData().get(respone.getData().size() - 1);
		mvc.perform(delete("/mobiles/" + lastMobile.getId())).andExpect(status().is(202));
	}

}
