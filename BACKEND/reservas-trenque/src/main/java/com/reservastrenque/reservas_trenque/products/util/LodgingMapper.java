package com.reservastrenque.reservas_trenque.products.util;

import com.reservastrenque.reservas_trenque.products.dto.AddressResponse;
import com.reservastrenque.reservas_trenque.products.dto.LodgingRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.location.model.City;
import com.reservastrenque.reservas_trenque.products.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LodgingMapper {

    public static LodgingResponse toResponse(Lodging lodging) {
        AddressResponse addressResponse = AddressResponse.builder()
                .street(lodging.getAddress().getStreet())
                .number(lodging.getAddress().getNumber())
                .city(lodging.getAddress().getCity().getName())
                .province(lodging.getAddress().getCity().getProvince().getName())
                .country(lodging.getAddress().getCity().getProvince().getCountry().getName())
                .build();

        return LodgingResponse.builder()
                .id(lodging.getId())
                .name(lodging.getName())
                .description(lodging.getDescription())
                .dailyPrice(lodging.getDailyPrice())
                .capacity(lodging.getCapacity())
                .lodgingType(lodging.getType().getName())
                .features(lodging.getFeatures().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .imageUrls(lodging.getImageUrls())
                .address(addressResponse)
                .responsibleName(lodging.getResponsible().getFullName())
                .responsibleEmail(lodging.getResponsible().getEmail())
                .build();
    }


    public static Lodging toEntity(
            LodgingRequest request,
            LodgingType type,
            Responsible responsible,
            City city
    ) {
        Address address = Address.builder()
                .street(request.getAddress().getStreet())
                .number(request.getAddress().getNumber())
                .city(city)
                .build();

        return Lodging.builder()
                .name(request.getName())
                .description(request.getDescription())
                .dailyPrice(request.getDailyPrice())
                .capacity(request.getCapacity())
                .type(type)
                .features(request.getFeatures())
                .responsible(responsible)
                .address(address)
                .build();
    }
}
