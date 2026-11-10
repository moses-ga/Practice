package io.kenzo.learning.exception;

/** A requested record does not exist. Unchecked so services stay clean; controllers catch it and flash a message. */
public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException(String message) {
        super(message);
    }
}
