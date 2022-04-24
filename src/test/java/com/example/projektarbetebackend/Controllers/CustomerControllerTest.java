package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository mockRepository;

    @BeforeEach
    public void init() {

        Customer c1 = new Customer(1L, "Fredrik", "Fredväg");
        Customer c2 = new Customer(2L, "Erik", "Eriväg");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(c1));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(c1, c2));
    }

    @Test
    void addNewProduct() throws Exception {
        String requestBody = "{\"name\":\"Alicia\",\"address\":\"Aliväg\"}";
        mvc.perform(MockMvcRequestBuilders.post("/customers/add")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getAllCustomers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Fredrik\",\"adress\":\"Fredväg\"}," +
                        "{\"id\":2,\"name\":\"Erik\",\"adress\":\"Eriväg\"}"));
    }

    @Test
    void getById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers/:1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Fredrik\",\"adress\":\"Fredväg\"}"));
    }
}