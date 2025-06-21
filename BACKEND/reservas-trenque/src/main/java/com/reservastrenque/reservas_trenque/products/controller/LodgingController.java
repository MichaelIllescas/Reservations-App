package com.reservastrenque.reservas_trenque.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservastrenque.reservas_trenque.products.dto.LodgingRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.usecase.CreateLodgingUseCase;
import com.reservastrenque.reservas_trenque.products.usecase.DeleteLodgingUseCase;
import com.reservastrenque.reservas_trenque.products.usecase.GetAllLodgingsUseCase;
import com.reservastrenque.reservas_trenque.shared.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequestMapping("/lodgings")
@RequiredArgsConstructor
public class LodgingController {

    private final GetAllLodgingsUseCase getAllLodgingsUseCase;
    private final CreateLodgingUseCase createLodgingUseCase;
    private final DeleteLodgingUseCase deleteLodgingUseCase;


    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Registrar nuevo alojamiento con imágenes",
            description = """
                    Este endpoint permite registrar un nuevo alojamiento.
                    <br><br>
                    📌 El campo <code>lodging</code> debe enviarse como un <b>String plano</b> que contenga un JSON con la estructura de <code>LodgingRequest</code>.
                    <br>
                    📷 El campo <code>images</code> permite adjuntar una o más imágenes del alojamiento.
                    """
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<LodgingResponse>> createLodging(

            @Parameter(
                    name = "lodging",
                    description = "JSON como string plano representando un LodgingRequest",
                    required = true,
                    schema = @Schema(implementation = LodgingRequest.class),
                    examples = {
                            @ExampleObject(
                                    name = "Ejemplo LodgingRequest",
                                    value = """
                                            {
                                              "name": "Hotel Simón",
                                              "description": "Alojamiento cómodo",
                                              "dailyPrice": 35000,
                                              "capacity": 4,
                                              "lodgingTypeId": 1,
                                              "features": ["WIFI", "TV", "AIR_CONDITIONING"],
                                              "address": {
                                                "street": "Av. San Martín",
                                                "number": "123",
                                                "cityId": 1
                                              },
                                              "responsible": {
                                                "fullName": "Laura Gómez",
                                                "email": "laura@ejemplo.com",
                                                "phone": "1122334455",
                                                "documentNumber": "37757084",
                                                "address": {
                                                  "street": "Belgrano",
                                                  "number": "456",
                                                  "cityId": 1
                                                }
                                              }
                                            }
                                            """
                            )
                    }
            )
            @RequestPart("lodging") String lodgingJson,

            @Parameter(
                    name = "images",
                    description = "Imágenes del alojamiento (formatos JPG, PNG). Enviar una o varias.",
                    required = true
            )
            @RequestPart("images") MultipartFile[] images
    ) {
        try {
            LodgingRequest request = objectMapper.readValue(lodgingJson, LodgingRequest.class);
            LodgingResponse response = createLodgingUseCase.execute(request, images);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Alojamiento creado exitosamente", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error al procesar la solicitud: " + e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Obtener todos los alojamientos",
            description = "Este endpoint devuelve una lista con todos los alojamientos registrados."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lista de alojamientos obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LodgingResponse.class))
            )
    })


    @GetMapping("/getLodgings")
    public ResponseEntity<ApiResponse<List<LodgingResponse>>> getAllLodgings() {
        List<LodgingResponse> lodgings = getAllLodgingsUseCase.execute();
        return ResponseEntity.ok(new ApiResponse<>("Lista de alojamientos obtenida exitosamente", lodgings));
    }



    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un alojamiento por ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Alojamiento eliminado exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Alojamiento no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteLodging(@PathVariable Long id) {
        deleteLodgingUseCase.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("Alojamiento eliminado correctamente", null ));
    }
}