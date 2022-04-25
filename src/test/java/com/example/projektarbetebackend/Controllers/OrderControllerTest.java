package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Models.DTO.BuyRequest;
import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

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

        BuyRequest bo1 = new BuyRequest(c1.getId(), i1.getId());
        BuyRequest bo2 = new BuyRequest(c2.getId(), i2.getId());

        when(mockOrderRepository.findAllByCustomerId(1L)).thenReturn(Arrays.asList(bo1));
        when(mockOrderController.getOrdersByCustId(1L)).thenReturn(Arrays.asList(bo1));
        when(mockOrderRepository.findAll()).thenReturn(Arrays.asList(bo1, bo2));
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void getOrdersByCustId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/:1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"customer\":{\"id\":10,\"name\":\"Henrik\"},\"item\":{\"id\":20,\"name\":\"A thing\"}}," +
                    "{\"id\":4,\"customer\":{\"id\":10,\"name\":\"Henrik\"},\"item\":{\"id\":22,\"name\":\"Phone\"}}]"));
    }
}