package com.app.service.car_statiscics;

import java.math.BigDecimal;

public record BigDecimalStatistics(
        BigDecimal min,
        BigDecimal avg,
        BigDecimal max,
        BigDecimal sum,
        int count
) {
}
