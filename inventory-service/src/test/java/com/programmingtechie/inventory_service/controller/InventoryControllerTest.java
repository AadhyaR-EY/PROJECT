package com.programmingtechie.inventory_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmingtechie.inventory_service.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
@ExtendWith(SpringExtension.class)
public class InventoryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private InventoryService inventoryService;

    @Test
    void isInStock_ShouldReturnTrue_WhenInStock() throws Exception {

        String skuCode = "iphone_13";
        when(inventoryService.isInStock(skuCode)).thenReturn(true);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/{sku-code}", skuCode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    void isInStock_ShouldReturnFalse_WhenOutOfStock() throws Exception {

        String skuCode = "iphone_13_red";
        when(inventoryService.isInStock(skuCode)).thenReturn(false);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/{sku-code}", skuCode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

}
