package com.programmingtechie.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmingtechie.order_service.dto.OrderLineItemsDto;
import com.programmingtechie.order_service.dto.OrderRequest;
import com.programmingtechie.order_service.model.OrderLineItems;
import com.programmingtechie.order_service.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;

    @Test
    void placeOrder_Test() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderLineItemsDtoList(getOrderLineItems());
        String orderRequestString = objectMapper.writeValueAsString(orderRequest);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/order").contentType(MediaType.APPLICATION_JSON).content(orderRequestString))
                .andExpect(status().isCreated())
                .andExpect(content().string("Order Placed Successfully"))
                        .andReturn();
        Assertions.assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals("Order Placed Successfully", mvcResult.getResponse().getContentAsString());
    }
    private  List<OrderLineItemsDto> getOrderLineItems() {

     OrderLineItemsDto order1 = OrderLineItemsDto.builder()
                .skuCode("iphone_13")
                .price(BigDecimal.valueOf(1200))
                .quantity(1)
                .build();

        OrderLineItemsDto order2 = OrderLineItemsDto.builder()
                .skuCode("iphone_12")
                .price(BigDecimal.valueOf(1250))
                .quantity(1)
                .build();
        return List.of(order1, order2);

    }

}
