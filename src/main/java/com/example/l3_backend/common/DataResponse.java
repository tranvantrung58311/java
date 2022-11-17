package com.example.l3_backend.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DataResponse {
    public static ResponseEntity<Object> setDataCustom(Object body, String errorMessage, String errorCode, HttpStatus httpStatus) {
        Data data = new Data(body, errorMessage, errorCode);
        return new ResponseEntity(data, httpStatus);
    }


    @Getter
    private static class Data {
        private Object data;
        private String errorMessage;
        private String errorCode;

        private Data(Object data, String errorMessage, String errorCode) {
            this.data = data;
            this.errorMessage = errorMessage;
            this.errorCode = errorCode;
        }
    }
}
