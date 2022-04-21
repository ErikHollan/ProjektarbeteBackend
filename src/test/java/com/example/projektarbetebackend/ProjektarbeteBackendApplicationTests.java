package com.example.projektarbetebackend;

import com.example.projektarbetebackend.Controllers.CustomerController;
import com.example.projektarbetebackend.Controllers.ItemController;
import com.example.projektarbetebackend.Controllers.OrderController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest
class ProjektarbeteBackendApplicationTests {


    @InjectMocks
    CustomerController customerController;

    @InjectMocks
    ItemController itemController;

    @InjectMocks
    OrderController orderController;

    @Test
    void contextLoads() {
    }

    @Test public void testAddCustomer(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

}
