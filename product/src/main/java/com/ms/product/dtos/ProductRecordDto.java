package com.ms.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductRecordDto(
        @NotBlank String category,
        @NotNull Float value,
        @NotBlank String name
) {
}
