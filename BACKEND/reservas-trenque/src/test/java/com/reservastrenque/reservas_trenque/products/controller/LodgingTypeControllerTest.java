package com.reservastrenque.reservas_trenque.products.controller;

import com.reservastrenque.reservas_trenque.products.model.LodgingType;
import com.reservastrenque.reservas_trenque.products.usecase.LodgingTypeUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LodgingTypeController.class)
class LodgingTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LodgingTypeUseCase lodgingTypeUseCase;

    @Test
    void getAllReturnsActiveTypes() throws Exception {
        LodgingType type = LodgingType.builder().name("Hotel").active(true).build();
        when(lodgingTypeUseCase.getAllActive()).thenReturn(List.of(type));

        mockMvc.perform(get("/lodging-types").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Hotel"));
    }
}
