package org.ekal.ivd.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class WebExceptionHandlerController {
    private final static Logger logger = LoggerFactory.getLogger(WebExceptionHandlerController.class);

    @ExceptionHandler(DynamicException.class)
    public ResponseEntity<ErrorResponseDTO> handleAppException(HttpServletResponse res, DynamicException e) {

        logger.error("WebExceptionHandlerController : handleAppExceptions : Exception - "+ ExceptionUtils.getStackTrace(e));

        ErrorResponseDTO errorResponseDTO = Optional.ofNullable(e.getError())
                .orElse(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, e.getStatus());
    }
}
