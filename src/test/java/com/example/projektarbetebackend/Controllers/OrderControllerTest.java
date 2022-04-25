package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Models.Orders;
import com.example.projektarbetebackend.Repositories.OrderRepository;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private OrderRepository mockOrderRepository;

    @MockBean
    private OrderController mockOrderController;




    @BeforeEach
    public void init() {
        Customer c1 = new Customer(1L, "1111", "väg1111");
        Customer c2 = new Customer(2L, "2222", "väg2222");

        Items i1 = new Items(1L, "1111", "item1111");
        Items i2 = new Items(2L, "2222", "item2222");

        Orders o1 = new Orders(1L, c1, i1);
        Orders o2 = new Orders(2L, c2, i2);


        when(mockOrderController.getAllOrders()).thenReturn(Arrays.asList(o1, o2));
        when(mockOrderController.getOrdersByCustId(1L)).thenReturn(Arrays.asList(o1));
        /*when(mockOrderRepository.findAllByCustomerId(1L)).thenReturn(Arrays.asList(o1));
        when(mockOrderRepository.findAll()).thenReturn(Arrays.asList(o1, o2));*/
    }

    @Test
    void getAllOrders() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"customer\":{\"id\":1,\"name\":\"1111\",\"address\": \"väg1111\"},\"item\":{\"id\":1,\"itemNumber\":\"1111\",\"name\":\"item1111\"}}," +
                    "{\"id\":2,\"customer\":{\"id\":2,\"name\":\"2222\",\"address\": \"väg2222\"},\"item\":{\"id\":2,\"itemNumber\":\"2222\",\"name\":\"item2222\"}}]"));

    }

    @Test
    void getOrdersByCustId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/customerId?custId=1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\": 1,\"customer\": {\"id\": 1,\"name\": \"1111\",\"address\": \"väg1111\"},\"item\":{\"id\": 1,\"itemNumber\": \"1111\",\"name\": \"item1111\"}}]"));
                                                    /*{"id": 1,"customer": {"id": 1,"name": "customer1","address": "address1"},"item": {"id": 1,"itemNumber": "1","name": "item1"}}*/
    }
    @Test
    void newOrder() throws Exception {
        Customer c1 = new Customer(1L, "cust1111", "väg1111");
        Items i3 = new Items(3L, "3333", "item3333");
        Orders order = new Orders(1L, c1, i3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/orders/buy")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(order));

        mvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("Order has been placed.")));

    }
}