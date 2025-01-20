package com.sahil.stock.info.dto.getDailyTs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDailyTsRequest {
    @NotBlank(message = "Symbol is required")
    private String symbol;
}
