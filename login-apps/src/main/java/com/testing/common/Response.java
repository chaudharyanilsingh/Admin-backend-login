package com.testing.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

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
	
