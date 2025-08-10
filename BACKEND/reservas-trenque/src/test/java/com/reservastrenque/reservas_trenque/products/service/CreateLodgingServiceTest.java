package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.config.exception.EmailAlreadyUsedException;
import com.reservastrenque.reservas_trenque.products.dto.*;
import com.reservastrenque.reservas_trenque.products.location.model.City;
import com.reservastrenque.reservas_trenque.products.location.model.Country;
import com.reservastrenque.reservas_trenque.products.location.model.Province;
import com.reservastrenque.reservas_trenque.products.model.*;
import com.reservastrenque.reservas_trenque.products.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateLodgingServiceTest {

    @Mock
    private LodgingRepository lodgingRepository;
    @Mock
    private LodgingTypeRepository lodgingTypeRepository;
    @Mock
    private ResponsibleRepository responsibleRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private FeatureRepository featureRepository;

    @InjectMocks
    private CreateLodgingService createLodgingService;

    @TempDir
    Path tempDir;

    private LodgingRequest buildRequest() {
        AddressRequest addressRequest = AddressRequest.builder()
                .street("Street")
                .number("123")
                .cityId(1L)
                .build();

        ResponsibleRequest responsibleRequest = new ResponsibleRequest(
                "John Doe",
                "john@example.com",
                "123456",
                "987654",
                addressRequest
        );

        return LodgingRequest.builder()
                .name("Lodge")
                .description("Nice place")
                .dailyPrice(BigDecimal.valueOf(100))
                .capacity(4)
                .lodgingTypeId(1L)
                .responsible(responsibleRequest)
                .featureIds(Set.of(1L))
                .address(addressRequest)
                .build();
    }

    private void mockCommonRepositories() {
        Country country = Country.builder().id(1L).name("Country").build();
        Province province = Province.builder().id(1L).name("Province").country(country).build();
        City city = City.builder().id(1L).name("City").province(province).build();
        LodgingType type = LodgingType.builder().id(1L).name("House").build();
        Feature feature = Feature.builder().id(1L).name("Wifi").icon("icon").build();

        when(lodgingTypeRepository.findById(1L)).thenReturn(Optional.of(type));
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(city));
        when(featureRepository.findAllById(any())).thenReturn(List.of(feature));
        when(responsibleRepository.save(any(Responsible.class))).thenAnswer(inv -> inv.getArgument(0));
        when(lodgingRepository.save(any(Lodging.class))).thenAnswer(invocation -> {
            Lodging lodging = invocation.getArgument(0);
            if (lodging.getId() == null) {
                lodging.setId(1L);
            }
            return lodging;
        });
    }

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(createLodgingService, "baseUploadPath", tempDir.toString());
    }

    @Test
    void execute_success_returnsResponseWithImageUrls() throws Exception {
        LodgingRequest request = buildRequest();
        mockCommonRepositories();
        when(responsibleRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(lodgingRepository.findByName("Lodge")).thenReturn(Optional.empty());

        MultipartFile image = mock(MultipartFile.class);
        when(image.getOriginalFilename()).thenReturn("photo.jpg");
        when(image.getBytes()).thenReturn("dummy".getBytes());

        LodgingResponse response = createLodgingService.execute(request, new MultipartFile[]{image});

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertFalse(response.getImageUrls().isEmpty());
        assertTrue(response.getImageUrls().get(0).startsWith("/images/alojamiento1/"));
    }

    @Test
    void execute_responsibleEmailExists_throwsEmailAlreadyUsedException() {
        LodgingRequest request = buildRequest();
        mockCommonRepositories();
        when(responsibleRepository.findByEmail("john@example.com")).thenReturn(Optional.of(new Responsible()));

        MultipartFile image = mock(MultipartFile.class);
        when(image.getOriginalFilename()).thenReturn("photo.jpg");
        when(image.getBytes()).thenReturn("dummy".getBytes());

        assertThrows(EmailAlreadyUsedException.class,
                () -> createLodgingService.execute(request, new MultipartFile[]{image}));
    }

    @Test
    void execute_duplicateLodgingName_throwsIllegalArgumentException() {
        LodgingRequest request = buildRequest();
        mockCommonRepositories();
        when(responsibleRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(lodgingRepository.findByName("Lodge")).thenReturn(Optional.of(new Lodging()));

        MultipartFile image = mock(MultipartFile.class);
        when(image.getOriginalFilename()).thenReturn("photo.jpg");
        when(image.getBytes()).thenReturn("dummy".getBytes());

        assertThrows(IllegalArgumentException.class,
                () -> createLodgingService.execute(request, new MultipartFile[]{image}));
    }

    @Test
    void execute_withoutImages_throwsIllegalArgumentException() {
        LodgingRequest request = buildRequest();
        mockCommonRepositories();
        when(responsibleRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(lodgingRepository.findByName("Lodge")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> createLodgingService.execute(request, new MultipartFile[0]));
    }

    @Test
    void execute_invalidImageExtension_throwsIllegalArgumentException() throws Exception {
        LodgingRequest request = buildRequest();
        mockCommonRepositories();
        when(responsibleRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(lodgingRepository.findByName("Lodge")).thenReturn(Optional.empty());

        MultipartFile image = mock(MultipartFile.class);
        when(image.getOriginalFilename()).thenReturn("photo.txt");
        when(image.getBytes()).thenReturn("dummy".getBytes());

        assertThrows(IllegalArgumentException.class,
                () -> createLodgingService.execute(request, new MultipartFile[]{image}));
    }
}

