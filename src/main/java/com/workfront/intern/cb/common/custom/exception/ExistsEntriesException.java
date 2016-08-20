package com.workfront.intern.cb.common.custom.exception;

public class ExistsEntriesException extends Exception {

    public ExistsEntriesException() {
        super();
    }

    public ExistsEntriesException(String message) {
        super(message);
    }

    public ExistsEntriesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsEntriesException(Throwable cause) {
        super(cause);
    }
}