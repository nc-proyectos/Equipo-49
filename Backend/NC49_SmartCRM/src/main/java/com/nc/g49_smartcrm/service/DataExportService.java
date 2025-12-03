package com.nc.g49_smartcrm.service;


import jakarta.servlet.http.HttpServletResponse;

public interface DataExportService {
    void exportConversationPdf( Long conversationId, HttpServletResponse response) throws Exception;
    void exportContacts(Long userId, HttpServletResponse response) throws Exception;
    void exportMetricsPdf(Long userId, HttpServletResponse response) throws Exception;
}
