package com.reservastrenque.reservas_trenque.products.util;

import com.reservastrenque.reservas_trenque.products.dto.*;
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
                .description(lodging.getDescription())
                .name(lodging.getName())
                .dailyPrice(lodging.getDailyPrice())
                .capacity(lodging.getCapacity())
                .lodgingType(lodging.getType().getName())
                .features(lodging.getFeatures().stream()
                        .map(LodgingMapper::mapFeature)
                        .collect(Collectors.toSet()))
                .imageUrls(lodging.getImageUrls())
                .address(addressResponse)
                .responsibleName(lodging.getResponsible().getFullName())
                .responsibleEmail(lodging.getResponsible().getEmail())
                .build();
    }

    public static LodgingDetailResponse toDetailResponse(Lodging lodging) {
        AddressDetailResponse lodgingAddress = AddressDetailResponse.builder()
                .street(lodging.getAddress().getStreet())
                .number(lodging.getAddress().getNumber())
                .cityId(lodging.getAddress().getCity().getId())
                .provinceId(lodging.getAddress().getCity().getProvince().getId())
                .countryId(lodging.getAddress().getCity().getProvince().getCountry().getId())
                .build();

        AddressDetailResponse responsibleAddress = AddressDetailResponse.builder()
                .street(lodging.getResponsible().getAddress().getStreet())
                .number(lodging.getResponsible().getAddress().getNumber())
                .cityId(lodging.getResponsible().getAddress().getCity().getId())
                .provinceId(lodging.getResponsible().getAddress().getCity().getProvince().getId())
                .countryId(lodging.getResponsible().getAddress().getCity().getProvince().getCountry().getId())
                .build();

        ResponsibleDetailResponse responsible = ResponsibleDetailResponse.builder()
                .fullName(lodging.getResponsible().getFullName())
                .email(lodging.getResponsible().getEmail())
                .phone(lodging.getResponsible().getPhone())
                .documentNumber(lodging.getResponsible().getDocumentNumber())
                .address(responsibleAddress)
                .build();

        return LodgingDetailResponse.builder()
                .id(lodging.getId())
                .name(lodging.getName())
                .description(lodging.getDescription())
                .dailyPrice(lodging.getDailyPrice())
                .capacity(lodging.getCapacity())
                .lodgingTypeId(lodging.getType().getId())
                .featureIds(lodging.getFeatures().stream().map(Feature::getId).collect(Collectors.toSet()))
                .address(lodgingAddress)
                .responsible(responsible)
                .imageUrls(lodging.getImageUrls())
                .build();
    }

    private static FeatureResponse mapFeature(Feature feature) {
        return FeatureResponse.builder()
                .id(feature.getId())
                .name(feature.getName())
                .icon(feature.getIcon())
                .build();
    }
}

