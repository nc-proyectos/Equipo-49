package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.Channel;

public record ChannelCountDto(
        Channel channel,
        Long count
) {
}
