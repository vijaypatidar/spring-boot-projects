package com.example.my.logger;


import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractEventLogger implements EventLogger{
    private final ConsoleHelper consoleHelper;

    public AbstractEventLogger(String name) {
        this.consoleHelper = new ConsoleHelper(name);
    }

    @Value("${logging.mode:INFO}")
    protected String logType;

    @Override
    public void info(EventDetail eventDetail) {
        consoleHelper.save(eventDetail,"INFO");
    }

    @Override
    public void debug(EventDetail eventDetail) {
        consoleHelper.save(eventDetail,"DEBUG");
    }

}
