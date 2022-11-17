package com.example.l3_backend.exception;

import com.example.l3_backend.common.DataResponse;
import com.example.l3_backend.constant.ErrorsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandling {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandling.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> BadRequestException(ResponseStatusException ex) {
        logger.error("Exception business: " + ex.getReason());
        ex.printStackTrace();
        return DataResponse.setDataCustom(null, ex.getReason(), "400", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> processIOException(Exception ex) {
        logger.error("---Exception error code:---- " + ex.getMessage());
        ex.printStackTrace();
        return DataResponse.setDataCustom(ex.getMessage(), ErrorsConstant.ERROR_SYSTEM, "500", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
