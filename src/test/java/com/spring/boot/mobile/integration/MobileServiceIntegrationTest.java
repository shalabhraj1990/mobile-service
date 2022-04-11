package com.spring.boot.mobile.integration;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class MobileServiceIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetMobileById() throws Exception {
		mvc.perform(get("/mobiles/1").accept(MediaType.APPLICATION_JSON)

		).andExpect(status().is(202))
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.id").value(1)).andExpect(jsonPath("$.data.name").value("NOKIA"))
				.andExpect(jsonPath("$.data.price").value(10000.0));
	}

	@Test
	public void testGetMobileByIdWithError() throws Exception {
		mvc.perform(get("/mobiles/1111").accept(MediaType.APPLICATION_JSON)

		).andExpect(status().is(400))
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errors.code").value(1001))
				.andExpect(jsonPath("$.errors.message").value("mobile id not found !!!!"));
	}

	@Test
	public void testGetMobile() throws Exception {
		mvc.perform(get("/mobiles").accept(MediaType.APPLICATION_JSON)

		).andExpect(status().is(202))
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(7))).andExpect(jsonPath("$.data[1].id").value(2))
				.andExpect(jsonPath("$.data[1].name").value("SAMSUNG-XLS"))
				.andExpect(jsonPath("$.data[1].price").value(10000.0));
	}

}
