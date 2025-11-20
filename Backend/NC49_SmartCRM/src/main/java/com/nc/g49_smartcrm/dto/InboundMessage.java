package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.Channel;
import com.nc.g49_smartcrm.model.ContactSource;
import com.nc.g49_smartcrm.model.SenderType;

//dto generico para cualquier tipo de canal( wpp, mail, sms, etc)
//no hay problema si hay campos null
public record InboundMessage(
        Channel channel,
        ContactSource contactSource,
        SenderType senderType,
        String firstName,
        String lastName,
        String phone,
        String email,
        String message
) {
}
