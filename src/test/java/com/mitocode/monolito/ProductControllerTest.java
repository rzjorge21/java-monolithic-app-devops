package com.mitocode.monolito.controller;

import com.mitocode.monolito.service.ProductService;
import com.mitocode.monolito.model.Product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    void shouldReturnListOfProducts() throws Exception {
        when(service.getAllProducts()).thenReturn(List.of(
                new Product(1L, "Laptop", 1000.0)
        ));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    void shouldReturnSingleProduct() throws Exception {
        when(service.getProductById(2L)).thenReturn(new Product(2L, "Mouse", 50.0));

        mockMvc.perform(get("/api/products/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse"));
    }
}
