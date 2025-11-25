package com.nc.g49_smartcrm.dto;

import lombok.Data;

import java.util.List;

@Data
public class WhatsAppWebhook {
    private List<Entry> entry;

    @Data
    public static class Entry {
        private List<Change> changes;
    }

    @Data
    public static class Change {
        private Value value;
    }

    @Data
    public static class Value {
        private List<Message> messages;
    }

    @Data
    public static class Message {
        private String from;
        private String id;
        private String timestamp;
        private Text text;
        private String type;
    }

    @Data
    public static class Text {
        private String body;
    }
}

