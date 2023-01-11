package com.example.collectiveproject.Exceptions;

import java.util.Objects;

public class IncorrectCredentialsException extends Exception {
    final ErrorCode errorCode;

    public IncorrectCredentialsException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncorrectCredentialsException that = (IncorrectCredentialsException) o;
        return errorCode == that.errorCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode);
    }
}
