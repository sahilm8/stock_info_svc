package com.sahil.stock.info.dto.getIntradayTs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetIntradayTsRequest {
    @NotBlank(message = "Symbol is required")
    private String symbol;

    @NotBlank(message = "Interval is required")
    private String interval;
}
