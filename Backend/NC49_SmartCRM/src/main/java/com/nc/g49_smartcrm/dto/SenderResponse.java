package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.SenderType;

public record SenderResponse(
        SenderType senderType,
        Long senderId,
        String firstName,
        String lastName,
        String phone,
        String email
) {
}
