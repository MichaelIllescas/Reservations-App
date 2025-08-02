package com.reservastrenque.reservas_trenque.products.util;

import com.reservastrenque.reservas_trenque.products.dto.AddressResponse;
import com.reservastrenque.reservas_trenque.products.dto.FeatureResponse;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.model.Feature;
import com.reservastrenque.reservas_trenque.products.model.Lodging;
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
                        .map(this::mapFeature)
                        .collect(Collectors.toSet()))
                .imageUrls(lodging.getImageUrls())
                .address(addressResponse)
                .responsibleName(lodging.getResponsible().getFullName())
                .responsibleEmail(lodging.getResponsible().getEmail())
                .build();
    }

    private FeatureResponse mapFeature(Feature feature) {
        return FeatureResponse.builder()
                .id(feature.getId())
                .name(feature.getName())
                .icon(feature.getIcon())
                .build();
    }
}
