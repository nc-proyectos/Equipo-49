package com.nc.g49_smartcrm.dto;

import java.time.Instant;
import java.time.LocalDate;

public record CountPerDayDto(
        java.sql.Date date,
        Long count
) {
}
