package com.nc.g49_smartcrm.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.nc.g49_smartcrm.dto.WhatsAppWebhook;

public interface WhatsAppService {
    String verifyUrl(String mode,String challenge,String token);
    void receiveMessage(WhatsAppWebhook webhook);
    void sendTextMessage(String to, String message) throws NumberParseException;
}
