package com.example.my.logger;

public interface EventLogger {
    void info(EventDetail eventDetail);
    void warn(EventDetail eventDetail);
    void debug(EventDetail eventDetail);
    EventDetail create();
}
