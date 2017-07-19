package controller;

import org.junit.Test;
import org.springframework.test.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import by.epam.newsmanagement.controller.HomeController;

public class HomeControllerTest {

	@Test
	public void testHomePage() throws Exception{
		HomeController homeController = new HomeController();
		//Assert.assertEquals("index_2", homeController.home());
		MockMvc mockMvc =
				standaloneSetup(homeController).build();
		mockMvc.perform(get("/welcome"))
		.andExpect(view().name("index_2"));
	}
}
