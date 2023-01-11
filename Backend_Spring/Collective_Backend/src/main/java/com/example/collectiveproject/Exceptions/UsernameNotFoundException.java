package com.example.collectiveproject.Exceptions;

import java.util.Objects;

public class UsernameNotFoundException extends Exception {
    final ErrorCode errorCode;

    public UsernameNotFoundException(String message, ErrorCode errorCode) {
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
        UsernameNotFoundException that = (UsernameNotFoundException) o;
        return errorCode == that.errorCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode);
    }
}
