package com.reservastrenque.reservas_trenque.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingDetailResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal dailyPrice;
    private Integer capacity;
    private Long lodgingTypeId;
    private Set<Long> featureIds;
    private AddressDetailResponse address;
    private ResponsibleDetailResponse responsible;
    private List<String> imageUrls;
}

