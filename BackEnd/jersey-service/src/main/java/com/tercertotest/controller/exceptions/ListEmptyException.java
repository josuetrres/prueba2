package com.tercertotest.controller.exceptions;

public class ListEmptyException extends Exception {


    public ListEmptyException() {
    }

    /**
     * 
     * @param msg 
     */
    public ListEmptyException(String msg) {
        super(msg);
    }
}


