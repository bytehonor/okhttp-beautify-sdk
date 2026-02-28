package com.bytehonor.sdk.toolkit.http.exception;

/**
 * @author lijianqiang
 *
 */
public class HttpToolkitException extends RuntimeException {

    private static final long serialVersionUID = 8241747723232910227L;

    public HttpToolkitException() {
        super();
    }

    public HttpToolkitException(String message) {
        super(message);
    }

    public HttpToolkitException(Exception cause) {
        super(cause);
    }
}
