package com.testing.exception;
import org.springframework.http.HttpStatus;

import com.testing.enums.AllEnums.ResultCode;

/**
 * @author bharat
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 259282790338671056L;
    private ResultCode resultCode;
    private String description;
    private HttpStatus status;

    public ApplicationException(ResultCode resultCode, String message, HttpStatus status) {
        super(resultCode.name());
        this.resultCode = resultCode;
        this.description = message;
        this.status = status;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
