package com.capella.mp3.tag.cleaner.exceptions;

import java.util.concurrent.Callable;

/**
 * @author Ramesh Rajendran
 */
public class Exceptions {
    /**
     * @param o
     */
    public static void uncheck(Callable<?> o) {
        try {
            o.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
