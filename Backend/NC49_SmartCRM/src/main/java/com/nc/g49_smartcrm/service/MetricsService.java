package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.dto.SourceCountDto;
import com.nc.g49_smartcrm.model.ContactStatus;

import java.util.List;

public interface MetricsService {
    //El user es el dueño.

    /**
     * cuenta conversaciones totales por user.
     *
     * @param userId id del usuario dueño
     * @return cantidad de mensajes asociados al user.
     */
    Long countConversations(Long userId);

    /**
     * cuenta conversaciones por canal(wpp,email,etc) por user id.
     *
     * @param userId id del usuario dueño
     * @return lista de objetos {canal, cantidad}
     */
    List<ChannelCountDto> countConversationsByChannels(Long userId);

    /**
     * cuenta cantidad de conversaciones abiertas por user id.
     *
     * @param userId id del usuario dueño
     * @return cantidad de conversaciones abiertas
     */
    Long countOpenConversations(Long userId);

    /**
     * cuenta cantidad de conversaciones cerradas por user id.
     *
     * @param userId id del usuario dueño
     * @return cantidad de conversaciones cerradas
     */
    Long countClosedConversations(Long userId);

    /**
     * cuenta cantidad de mensajes enviados por el user/dueño.
     *
     * @param userId id del usuario dueño
     * @return cantidad de mensajes enviados
     */

    List<CountPerDayDto> countConversationsCreatedPerDay(Long userId);
    Double avgResolutionTime(Long userId);
    List<CountPerDayDto> countContactsPerDay(Long userId);
    List<ChannelCountDto>countMessageByChannel(Long userId);
    List<CountPerDayDto>countMessagePerDayByUser(Long userId);

    Long countMessagesSent(Long userId);

    /**
     * cuenta cantidad de mensajes recibidos del user/dueño
     *
     * @param userId id del usuario dueño
     * @return cantidad de mensajes recibidos
     */
    Long countMessagesReceived(Long userId);

    /**
     * cuenta contactos por contactStatus(en seguimiento o potencial cliente) y por user/dueño
     *
     * @param userId id del usuario dueño
     * @param status estado del cliente(lead active o in follow up
     * @return cantidad de contactos por estado
     */
    Long countContactsByStatus(Long userId, ContactStatus status);

    /**
     * obtiene los contactos filtrados por wpp, mail, etc.
     *
     * @param userId id del usuario dueño
     * @return lista de contactos agrupada por source(wpp, mail, etc)
     */
    List<SourceCountDto> countContactsBySource(Long userId);
}
