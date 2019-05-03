package com.example.demo;

import com.example.demo.controllers.HappyController;
import com.example.demo.services.RandomNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HappyController.class)
public class HappyControllerAPITest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RandomNumberService randomNumberService;

	@Test
	public void shouldReturnIsHappyPrime() throws Exception {

		mockMvc.perform(get("/v1/happyprime/2"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":2,\"isHappy\":false,\"isHappyPrime\":false,\"isPrime\":true}"));
		mockMvc.perform(get("/v1/happyprime/167"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":167,\"isHappy\":true,\"isHappyPrime\":true,\"isPrime\":true}"));
		mockMvc.perform(get("/v1/happyprime/100"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":100,\"isHappy\":true,\"isHappyPrime\":false,\"isPrime\":false}"));
		mockMvc.perform(get("/v1/happyprime/4"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":4,\"isHappy\":false,\"isHappyPrime\":false,\"isPrime\":false}"));
	}

	@Test
	public void shouldReturnIsHappyPrimeRandom() throws Exception {
		given(randomNumberService.getRandomNumber()).willReturn(2);
		mockMvc.perform(get("/v1/happyprime"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":2,\"isHappy\":false,\"isHappyPrime\":false,\"isPrime\":true}"));
		given(randomNumberService.getRandomNumber()).willReturn(167);
		mockMvc.perform(get("/v1/happyprime"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":167,\"isHappy\":true,\"isHappyPrime\":true,\"isPrime\":true}"));
		given(randomNumberService.getRandomNumber()).willReturn(100);
		mockMvc.perform(get("/v1/happyprime"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":100,\"isHappy\":true,\"isHappyPrime\":false,\"isPrime\":false}"));
		given(randomNumberService.getRandomNumber()).willReturn(4);
		mockMvc.perform(get("/v1/happyprime"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":4,\"isHappy\":false,\"isHappyPrime\":false,\"isPrime\":false}"));
	}

	@Test
	public void shouldReturnIsHappyPrimeRandomInternalServerError() throws Exception {
		given(randomNumberService.getRandomNumber()).willReturn(0);
		mockMvc.perform(get("/v1/happyprime"))
				.andExpect(status().is5xxServerError())
				.andExpect(content().json("{\"error\":\"Error getting random number.\"}"));
	}

}
