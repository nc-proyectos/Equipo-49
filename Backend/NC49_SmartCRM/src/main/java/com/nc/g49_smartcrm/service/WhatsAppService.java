package com.nc.g49_smartcrm.service;

import java.util.Map;

public interface WhatsAppService {
    void enviarMensaje(String numeroDestino,String mensaje);
    String recibirMensaje(Map<String, String> datos);
}
