package com.spring.boot.mobile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest
@AutoConfigureMockMvc
public class MobileServiceApplicationTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetMobileById() throws Exception {
		mvc.perform(get("/mobiles/1").accept(org.springframework.http.MediaType.APPLICATION_JSON))
				.andExpect(status().is(202)).andExpect(content().contentType(MediaType.APPLICATION_JSON))

				.andExpect((ResultMatcher) jsonPath("$.data.id").value(1))
				.andExpect((ResultMatcher) jsonPath("$.data.name").value("NOKIA"))
				.andExpect((ResultMatcher) jsonPath("$.data.price").value(10000));


	}

}
