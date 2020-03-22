package com.example.web.entity;


import lombok.Data;
import lombok.Getter;

@Data
public class Counter {

    @Getter
    private static int counter = 0;

    private static Object lock = new Object();

    public static int reset() {
        counter = 0;
        return counter;
    }

    public synchronized void wrong() {
        counter++;
    }
    public  void right() {
        synchronized(lock){
            counter++;
        }
    }

}
