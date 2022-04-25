package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CustomerRepository mockRepository;

    @BeforeEach
    public void init() {

        Customer c1 = new Customer(1L, "1111", "väg1111");
        Customer c2 = new Customer(2L, "2222", "väg2222");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(c1));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(c1, c2));
    }

    @Test
    void addNewCustomer() throws Exception {

        Customer c3 = new Customer(3L, "Felix", "Felväg");
        when(mockRepository.save(c3)).thenReturn(c3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/customer/add")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(c3));

        mvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("Customer is saved.")));

        /*String requestBody = "{\"name\":\"Alicia\",\"address\":\"Aliväg\"}";
        mvc.perform(MockMvcRequestBuilders.post("/customer/add")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());*/

    }

    @Test
    void getAllCustomers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customer/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"name\":\"1111\",\"address\":\"väg1111\"}," +
            "{\"id\":2,\"name\":\"2222\",\"address\":\"väg2222\"}]"));
    }

    @Test
    void getById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customer/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\":1,\"name\":\"1111\",\"address\":\"väg1111\"}"));
    }
}