package com.example.my.logger;

public class ConsoleHelper {
    public ConsoleHelper(String name){

    }
    public void save(EventDetail eventDetail, String debug) {
        System.out.println(eventDetail+" "+debug);
    }
}
