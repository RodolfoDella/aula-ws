package br.com.wedosoft.jaxrs.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

    private String message;

    private Map<String, String> detailMessage = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDetailMessage() {
        return Collections.unmodifiableMap(detailMessage);
    }

    public Map<String, String> addDetailMessage(String attribute, String message) {
        detailMessage.put(attribute, message);
        return detailMessage;
    }

    public Map<String, String> addDetailMessage(Map<String, String> messages) {
        detailMessage.putAll(messages);
        return detailMessage;
    }

    public Map<String, String> removeDetailMessage(String message) {
        detailMessage.remove(message);
        return detailMessage;
    }

    public void clearDetailMessage() {
        detailMessage.clear();
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", detailMessage=" + detailMessage +
                '}';
    }

}
