package com.onlineshop.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Rollback(false)
public class CustomerRestControllerTests {
	
	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	@Autowired CustomerRepository custRepo;
	
	@Test
	
	public void testCheckUniqueEmailAndExpectDuplicated() throws JsonProcessingException, Exception {
		
		String url ="/check_unique";
		String email="s1112afta.leonard@yahoo.com";
		
		MvcResult result = mockMvc.perform(post(url).contentType("application/json")
				                                    .content(objectMapper.writeValueAsString(email))
				                                    .with(csrf()))
				                                    .andDo(print())
				                                    .andExpect(status().isOk())
				                                    .andDo(print())
				                                    .andReturn();
				
		String response = result.getResponse().getContentAsString();
		System.out.println("-------------------"  + response);
		 assertThat(response.contains("Duplicated"));
				
	}

}
