package com.hcl.hackathon.fullstack;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.hcl.hackathon.fullstack.auth.privilege.services.PrivilegeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FullStackApplicationTests {

	@Autowired
	private PrivilegeService privilegeServiceMock;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		Privilege p1 = new Privilege("Add User", "Adds a new User");
		Privilege p2 = new Privilege("Delete User", "Deletes a User");
		
		when(privilegeServiceMock.findAllPrivileges()).thenReturn(Arrays.asList(p1, p2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/privileges"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].privilegeName", is("Add User")))
        .andExpect(jsonPath("$[0].privilegeDescription", is("Adds a new User")))
        .andExpect(jsonPath("$[1].privilegeName", is("Delete User")))
        .andExpect(jsonPath("$[1].privilegeDescription", is("Deletes a User")));
		
		verify(privilegeServiceMock, times(1)).findAllPrivileges();
        verifyNoMoreInteractions(privilegeServiceMock);
	}
}
