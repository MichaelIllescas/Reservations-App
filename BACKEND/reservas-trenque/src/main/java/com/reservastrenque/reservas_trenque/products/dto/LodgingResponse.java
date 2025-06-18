package com.reservastrenque.reservas_trenque.products.dto;

import com.reservastrenque.reservas_trenque.products.model.LodgingFeature;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal dailyPrice;
    private Integer capacity;
    private String lodgingType;
    private Set<String> features;
    private List<String> imageUrls;
    private AddressResponse address;
    private String responsibleName;
    private String responsibleEmail;
}
