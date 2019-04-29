package com.yw.colliery.sdk.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author xuzhou
 *
 */
public class ValidCodeAuthenticationException extends AuthenticationException {
    public ValidCodeAuthenticationException(String msg) {
        super(msg);
    }
}
