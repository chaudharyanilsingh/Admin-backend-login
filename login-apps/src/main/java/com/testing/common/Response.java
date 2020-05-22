package com.testing.common;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response{

    private Object data;
    private String message;

    public Response( String message) {
        this.message = message;
    }

    public Response setResult(Object data) {
        this.data = data;
        return this;
    }
}
	
