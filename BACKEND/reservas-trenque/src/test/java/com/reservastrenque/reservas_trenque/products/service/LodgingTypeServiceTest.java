package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.model.LodgingType;
import com.reservastrenque.reservas_trenque.products.persistence.LodgingTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LodgingTypeServiceTest {

    @Mock
    private LodgingTypeRepository repository;

    @InjectMocks
    private LodgingTypeService service;

    @Test
    void getAllActiveReturnsOnlyActiveTypes() {
        LodgingType active = LodgingType.builder().active(true).build();
        LodgingType inactive = LodgingType.builder().active(false).build();
        when(repository.findAll()).thenReturn(List.of(active, inactive));

        List<LodgingType> result = service.getAllActive();

        assertEquals(1, result.size());
        assertTrue(result.contains(active));
    }
}
