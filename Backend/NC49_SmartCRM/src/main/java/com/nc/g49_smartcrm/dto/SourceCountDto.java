package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.ContactSource;

public record SourceCountDto(
        ContactSource source,
        Long count
) {
}
