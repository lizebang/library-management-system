package com.library.client.util;

public class LibraryException extends Exception {

    private final Integer status;

    public LibraryException(final Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String toString() {
        return String.format("Status: %d", status);
    }
}
