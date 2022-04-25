package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Models.DTO.BuyRequest;
import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Repositories.ItemRepository;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ItemRepository mockItemRepository;


    @BeforeEach
    public void init() {
        Items i1 = new Items(1L, "1111", "item1111");
        Items i2 = new Items(2L, "2222", "item2222");

        when(mockItemRepository.findAll()).thenReturn(Arrays.asList(i1, i2));
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(i1));
    }

    @Test
    void addNewProduct() throws Exception {
        Items i4 = new Items(4L, "4444", "item4444");
        when(mockItemRepository.save(i4)).thenReturn(i4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/items/add")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(i4));

        mvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("Item is saved.")));
    }

    @Test
    void newOrder() throws Exception {
        BuyRequest buyOrder = new BuyRequest(1L, 1L);
        mvc.perform(MockMvcRequestBuilders.post("/items/buy")
            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
            .content(stringWrapper(buyOrder)))
            .andExpect(status().isOk());
    }

    @Test
    void getAllProducts() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/items/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"itemNumber\":\"1111\",\"name\":\"item1111\"}," +
            "{\"id\":2,\"itemNumber\":\"2222\",\"name\":\"item2222\"}]"));
    }


    @Test
    void getitemById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/items/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\":1,\"itemNumber\":\"1111\",\"name\":\"item1111\"}"));
    }

    public static String stringWrapper(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}