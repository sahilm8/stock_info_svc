package com.sahil.stock.info.dto.getMonthlyTs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMonthlyTsRequest {
    @NotBlank(message = "Symbol is required")
    private String symbol;
}
