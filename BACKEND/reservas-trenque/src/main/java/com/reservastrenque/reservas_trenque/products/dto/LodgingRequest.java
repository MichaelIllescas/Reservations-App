package com.reservastrenque.reservas_trenque.products.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "LodgingRequest", description = "DTO para registrar un nuevo alojamiento")

public class LodgingRequest {

    @Schema(description = "Nombre del alojamiento", example = "Cabaña El Sol")
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String name;

    @Schema(description = "Descripción del alojamiento", example = "Una hermosa cabaña rodeada de naturaleza.")
    @NotBlank(message = "La descripción no puede estar vacía.")
    private String description;

    @Schema(description = "Precio por noche en moneda local", example = "19500.00")
    @NotNull(message = "El precio diario es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero.")
    private BigDecimal dailyPrice;

    @Schema(description = "Capacidad máxima de personas", example = "4")
    @NotNull(message = "La capacidad es obligatoria.")
    @Min(value = 1, message = "La capacidad debe ser al menos 1.")
    private Integer capacity;

    @Schema(description = "ID del tipo de alojamiento", example = "1")
    @NotNull(message = "El tipo de alojamiento es obligatorio.")
    private Long lodgingTypeId;

    @Schema(description = "Información del responsable del alojamiento")
    private ResponsibleRequest responsible;

    @Schema(description = "IDs de las características del alojamiento")
    @NotNull(message = "Las características son obligatorias.")
    private Set<Long> featureIds;

    @Schema(description = "Dirección del alojamiento")
    @NotNull(message = "La dirección es obligatoria.")
    private AddressRequest address;
}
