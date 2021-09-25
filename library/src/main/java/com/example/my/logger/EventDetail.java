package com.example.my.logger;

public class EventDetail implements Cloneable{
    private String message;

    public EventDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventDetail{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public EventDetail clone() {
        try {
            return (EventDetail) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
