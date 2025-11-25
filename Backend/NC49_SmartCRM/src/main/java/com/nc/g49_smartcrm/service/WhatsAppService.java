package com.nc.g49_smartcrm.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.nc.g49_smartcrm.dto.WhatsAppWebhook;

public interface WhatsAppService {
    void enviarMensaje(String numeroDestino, String mensaje);

    String recibirMensaje(Map<String, String> datos);
}
